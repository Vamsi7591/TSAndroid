package com.android.timesheet.shared.models;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;

/**
 * This is generated code. Please do not modify */
public final class User_Table extends ModelAdapter<User> {
  /**
   * Primary Key */
  public static final Property<String> adminempcode = new Property<String>(User.class, "adminempcode");

  public static final Property<String> empName = new Property<String>(User.class, "empName");

  public static final Property<String> empEmailid = new Property<String>(User.class, "empEmailid");

  public static final Property<String> checkPassword = new Property<String>(User.class, "checkPassword");

  public static final Property<String> empRole = new Property<String>(User.class, "empRole");

  public static final Property<String> empCode = new Property<String>(User.class, "empCode");

  public static final Property<String> date = new Property<String>(User.class, "date");

  public static final Property<String> projectName = new Property<String>(User.class, "projectName");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{adminempcode,empName,empEmailid,checkPassword,empRole,empCode,date,projectName};

  public User_Table(DatabaseDefinition databaseDefinition) {
    super(databaseDefinition);
  }

  @Override
  public final Class<User> getModelClass() {
    return User.class;
  }

  @Override
  public final String getTableName() {
    return "`user`";
  }

  @Override
  public final User newInstance() {
    return new User();
  }

  @Override
  public final Property getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch ((columnName)) {
      case "`adminempcode`":  {
        return adminempcode;
      }
      case "`empName`":  {
        return empName;
      }
      case "`empEmailid`":  {
        return empEmailid;
      }
      case "`checkPassword`":  {
        return checkPassword;
      }
      case "`empRole`":  {
        return empRole;
      }
      case "`empCode`":  {
        return empCode;
      }
      case "`date`":  {
        return date;
      }
      case "`projectName`":  {
        return projectName;
      }
      default: {
        throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
      }
    }
  }

  @Override
  public final IProperty[] getAllColumnProperties() {
    return ALL_COLUMN_PROPERTIES;
  }

  @Override
  public final void bindToInsertValues(ContentValues values, User model) {
    values.put("`adminempcode`", model.adminempcode != null ? model.adminempcode : null);
    values.put("`empName`", model.empName != null ? model.empName : null);
    values.put("`empEmailid`", model.emailId != null ? model.emailId : null);
    values.put("`checkPassword`", model.password != null ? model.password : null);
    values.put("`empRole`", model.empRole != null ? model.empRole : null);
    values.put("`empCode`", model.empCode != null ? model.empCode : null);
    values.put("`date`", model.date != null ? model.date : null);
    values.put("`projectName`", model.projectName != null ? model.projectName : null);
  }

  @Override
  public final void bindToInsertStatement(DatabaseStatement statement, User model, int start) {
    statement.bindStringOrNull(1 + start, model.adminempcode);
    statement.bindStringOrNull(2 + start, model.empName);
    statement.bindStringOrNull(3 + start, model.emailId);
    statement.bindStringOrNull(4 + start, model.password);
    statement.bindStringOrNull(5 + start, model.empRole);
    statement.bindStringOrNull(6 + start, model.empCode);
    statement.bindStringOrNull(7 + start, model.date);
    statement.bindStringOrNull(8 + start, model.projectName);
  }

  @Override
  public final void bindToUpdateStatement(DatabaseStatement statement, User model) {
    statement.bindStringOrNull(1, model.adminempcode);
    statement.bindStringOrNull(2, model.empName);
    statement.bindStringOrNull(3, model.emailId);
    statement.bindStringOrNull(4, model.password);
    statement.bindStringOrNull(5, model.empRole);
    statement.bindStringOrNull(6, model.empCode);
    statement.bindStringOrNull(7, model.date);
    statement.bindStringOrNull(8, model.projectName);
    statement.bindStringOrNull(9, model.adminempcode);
  }

  @Override
  public final void bindToDeleteStatement(DatabaseStatement statement, User model) {
    statement.bindStringOrNull(1, model.adminempcode);
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `user`(`adminempcode`,`empName`,`empEmailid`,`checkPassword`,`empRole`,`empCode`,`date`,`projectName`) VALUES (?,?,?,?,?,?,?,?)";
  }

  @Override
  public final String getUpdateStatementQuery() {
    return "UPDATE `user` SET `adminempcode`=?,`empName`=?,`empEmailid`=?,`checkPassword`=?,`empRole`=?,`empCode`=?,`date`=?,`projectName`=? WHERE `adminempcode`=?";
  }

  @Override
  public final String getDeleteStatementQuery() {
    return "DELETE FROM `user` WHERE `adminempcode`=?";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `user`(`adminempcode` TEXT, `empName` TEXT, `empEmailid` TEXT, `checkPassword` TEXT, `empRole` TEXT, `empCode` TEXT, `date` TEXT, `projectName` TEXT, PRIMARY KEY(`adminempcode`))";
  }

  @Override
  public final void loadFromCursor(FlowCursor cursor, User model) {
    model.adminempcode = cursor.getStringOrDefault("adminempcode");
    model.empName = cursor.getStringOrDefault("empName");
    model.emailId = cursor.getStringOrDefault("empEmailid");
    model.password = cursor.getStringOrDefault("checkPassword");
    model.empRole = cursor.getStringOrDefault("empRole");
    model.empCode = cursor.getStringOrDefault("empCode");
    model.date = cursor.getStringOrDefault("date");
    model.projectName = cursor.getStringOrDefault("projectName");
  }

  @Override
  public final boolean exists(User model, DatabaseWrapper wrapper) {
    return SQLite.selectCountOf()
    .from(User.class)
    .where(getPrimaryConditionClause(model))
    .hasData(wrapper);
  }

  @Override
  public final OperatorGroup getPrimaryConditionClause(User model) {
    OperatorGroup clause = OperatorGroup.clause();
    clause.and(adminempcode.eq(model.adminempcode));
    return clause;
  }
}
