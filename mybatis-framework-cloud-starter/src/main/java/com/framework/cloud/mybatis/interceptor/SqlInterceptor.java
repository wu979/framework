package com.framework.cloud.mybatis.interceptor;

import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wusiwei
 */
@SuppressWarnings({"rawtypes", "all"})
public class SqlInterceptor extends JsqlParserSupport implements InnerInterceptor {

    /**
     * 操作前置处理
     * <p>
     * 改改sql啥的
     *
     * @param executor      Executor(可能是代理对象)
     * @param ms            MappedStatement
     * @param parameter     parameter
     * @param rowBounds     rowBounds
     * @param resultHandler resultHandler
     * @param boundSql      boundSql
     */
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
                            ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (!InterceptorIgnoreHelper.willIgnoreTenantLine(ms.getId())) {
            if (!SqlParserHelper.getSqlParserInfo(ms)) {
                PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
                mpBs.sql(this.parserSingle(mpBs.sql(), (Object) null));
            }
        }
    }

    /**
     * 查询
     */
    @Override
    protected void processSelect(Select select, int index, Object obj) {
        SelectBody selectBody = select.getSelectBody();
        if (selectBody instanceof PlainSelect) {
            reformatPlainSelect((PlainSelect) selectBody);
        }
    }

    /**
     * 重新格式化 查询语句
     *
     * @param plainSelect 查询语句
     * @return 格式化的查询语句
     */
    public void reformatPlainSelect(PlainSelect plainSelect) {
        //处理要查询的字段
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        //处理查询条件
        plainSelect.setSelectItems(disposeSelectColumn(selectItems));
        //处理 where 条件
        plainSelect.setWhere(disposeSelectWhere(plainSelect.getWhere()));

    }

    /**
     * 处理查询字段
     */
    private List<SelectItem> disposeSelectColumn(List<SelectItem> selectItems) {
        return selectItems.stream().map(this::resetSelectItem).collect(Collectors.toList());
    }

    private SelectItem resetSelectItem(SelectItem selectItem) {
        //如果不符合直接返回
        if (!(selectItem instanceof SelectExpressionItem)) {
            return selectItem;
        }
        SelectExpressionItem item = (SelectExpressionItem) selectItem;
        //如果是列
        if (item.getExpression() instanceof Column) {
            Column columnExp = (Column) item.getExpression();
            return new SelectExpressionItem(reFormatSelectColumn(columnExp, item.getAlias()));
        }
        //如果是函数
        if (item.getExpression() instanceof Function) {
            Function function = (Function) item.getExpression();
            return new SelectExpressionItem(reFormatFunction(function));
        }
        return item;
    }

    /**
     * 重新格式化列
     *
     * @param columnExp 列
     * @param alias     列别名
     * @return 格式化的列
     */
    private Column reFormatSelectColumn(Column columnExp, Alias alias) {
        if (columnExp == null) {
            return columnExp;
        }
        //表名和列簇名会在一起
        String tableAndCFName = columnExp.getTable() == null ? "" : columnExp.getTable().toString();
        //字段名
        String columnName = columnExp.getColumnName();
        //根据 `.` 分隔方便处理表名和列簇名
        String[] tableAndCFInfo = tableAndCFName.split("\\.");
        // 可能会出现很多情况 列名  列簇.列名  表名.列簇.列名 表名.列名
        String tableName = tableAndCFInfo[0];
        String cf = tableAndCFInfo[tableAndCFInfo.length - 1];
        //如果表名和字段名相等,只有3种情况: 列名  表名.列名  列簇.列名
        if (StringUtils.equals(tableName, cf) && StringUtils.isNotBlank(tableName)) {
            //判断前缀是表名还是列名  要求列簇必须全大写 表名不能全大写
            //如果全大写这是列簇名
            if (StringUtils.equals(cf.toUpperCase(), cf)) {
                tableName = "";
            } else {
                //否则是表名
                cf = "";
            }
        }
        StringBuilder finalName = new StringBuilder();
        //如果表名不为空 拼接表名
        if (StringUtils.isNotBlank(tableName)) {
            finalName.append(tableName).append(".");
        }
        //如果列簇名不为空 拼接列簇名
        if (StringUtils.isNotBlank(cf)) {
            finalName.append(appendPrefixAndSuffix(cf)).append(".");
        }
        //拼接字段名
        finalName.append(appendPrefixAndSuffix(columnName));
        //拼接别名: as xxx
        if (alias != null) {
            finalName.append(" ").append(alias.getName());
        }
        //重新格式化列名 封装返回
        return new Column(finalName.toString());
    }

    /**
     * 重新格式化查询函数
     *
     * @param function 函数
     * @return 格式化的函数
     */
    private Function reFormatFunction(Function function) {
        List<Expression> expressions = function.getParameters().getExpressions();
        //对于是列的参数进行格式化
        expressions = expressions.stream().map(exp -> {
            if (exp instanceof Column) return reFormatSelectColumn((Column) exp, null);
            return exp;
        }).collect(Collectors.toList());
        //重新设置回去
        function.getParameters().setExpressions(expressions);
        return function;
    }


    /**
     * 重新格式化子查询
     *
     * @param subSelect 子查询
     * @return 格式化的函数
     */
    private SubSelect reFormatSubSelect(SubSelect subSelect) {
        if (subSelect.getSelectBody() instanceof PlainSelect) {
            reformatPlainSelect((PlainSelect) subSelect.getSelectBody());
        }
        return subSelect;
    }


    public Expression disposeSelectWhere(Expression expression) {
        if (!(expression instanceof BinaryExpression)) {
            return expression;
        }
        BinaryExpression binaryExpression = (BinaryExpression) expression;
        //如果左边还是多条件的
        if (binaryExpression.getLeftExpression() instanceof BinaryExpression) {
            disposeSelectWhere(binaryExpression.getLeftExpression());
        }
        //如果右边还是多条件的
        if (binaryExpression.getRightExpression() instanceof BinaryExpression) {
            disposeSelectWhere(binaryExpression.getRightExpression());
        }
        //如果左边表达式是列信息 格式化
        if (binaryExpression.getLeftExpression() instanceof Column) {
            Column newColumn = reFormatSelectColumn((Column) binaryExpression.getLeftExpression(), null);
            binaryExpression.setLeftExpression(newColumn);
        }
        //如果左边表达式是 子查询 processPlainSelect
        if (binaryExpression.getLeftExpression() instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) binaryExpression.getLeftExpression();
            if (subSelect.getSelectBody() instanceof PlainSelect) {
                reformatPlainSelect((PlainSelect) subSelect.getSelectBody());
            }
        }
        //如果右边是列信息 格式化
        if (binaryExpression.getRightExpression() instanceof Column) {
            Column newColumn = reFormatSelectColumn((Column) binaryExpression.getLeftExpression(), null);
            binaryExpression.setRightExpression(newColumn);
        }
        //如果右边表达式是 子查询 processPlainSelect
        if (binaryExpression.getRightExpression() instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) binaryExpression.getRightExpression();
            reFormatSubSelect(subSelect);
        }
        return binaryExpression;
    }


    private String appendPrefixAndSuffix(String str) {
        final String PREFIX = "`";
        final String SUFFIX = "`";
        //如果已经有前缀了直接返回
        if (str.contains(PREFIX)) {
            return str;
        }
        //拼接前缀和后缀
        return new StringBuilder().append(PREFIX).append(str).append(SUFFIX).toString();
    }

}
