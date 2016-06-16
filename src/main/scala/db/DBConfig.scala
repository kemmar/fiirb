package db

import net.fwbrasil.activate.ActivateContext
import net.fwbrasil.activate.storage.relational.PooledJdbcRelationalStorage
import net.fwbrasil.activate.storage.relational.idiom.mySqlDialect

object DBConfig extends ActivateContext {
  override protected def entitiesPackages = List("domain")
  val storage = new PooledJdbcRelationalStorage {
    override val jdbcDriver = "com.mysql.jdbc.Driver"
    override val user = Some("root")
    override val password = None
    override val url = "jdbc:mysql://localhost/fiirb"
    override val dialect = mySqlDialect
  }
}
