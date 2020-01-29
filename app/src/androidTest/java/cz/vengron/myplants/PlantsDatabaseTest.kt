package cz.vengron.myplants

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.database.PlantsDatabase
import cz.vengron.myplants.database.PlantsDatabaseDao
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import java.lang.Exception

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PlantDatabaseTest {

    private lateinit var plantDao: PlantsDatabaseDao
    private lateinit var db: PlantsDatabase

    @Before
    fun createDB() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(appContext, PlantsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        plantDao = db.plantsDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetPlant() {
        val plant = Plant()
        plantDao.insert(plant)
        val oldPlant = plantDao.getLastPlant()
        assertEquals(oldPlant.plantId, 1)
    }
}
