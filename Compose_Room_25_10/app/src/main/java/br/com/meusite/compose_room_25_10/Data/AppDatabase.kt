package br.com.meusite.compose_room_25_10.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Livro::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun livroDao(): LivroDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /*
        * start(){
        *   if (INSTANCE != null){
        *       -----
        *   } else{
        *       INSTANCE = new LivroDatabase()
        *   }
        *
        *   return INSTANCE;
        *
        * }
        *
        * */


        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }else{

                synchronized(this){

                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "livro_table"
                    ).build()

                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}