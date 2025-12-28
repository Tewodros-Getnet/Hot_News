package com.example.hotnews.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.hotnews.models.Article

@TypeConverters(Converters::class)
@Database(
    entities = [Article::class],
    version = 1,
//    exportSchema = false  // Optional: set to true if you want to export schema for migrations
)
abstract class ArticleDatabase : RoomDatabase() {  // Added 'class' keyword
    abstract fun getArticleDao(): ArticleDAO

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): ArticleDatabase =
            instance ?: synchronized(LOCK) {
                instance ?: createDatabase(context).also { instance = it }
            }

        private fun createDatabase(context: Context): ArticleDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"  // Consider simpler name like "article_database.db"
            ).build()
    }
}