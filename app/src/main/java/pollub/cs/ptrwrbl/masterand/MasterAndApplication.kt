package pollub.cs.ptrwrbl.masterand

import android.app.Application

class MasterAndApplication: Application() {
    lateinit var container: AppDataContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}