package cz.vengron.myplants.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class PlantsDatabase: RoomDatabase() {

    abstract val plantsDatabaseDao: PlantsDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: PlantsDatabase? = null

        fun getInstance(context: Context): PlantsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlantsDatabase::class.java,
                        "plants_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}