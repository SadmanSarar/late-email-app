package com.sadmansarar.app.latemailsend.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sadmansarar.app.latemailsend.data.Constants
import com.sadmansarar.app.latemailsend.data.api.jsonParser.DateTimeParser
import com.sadmansarar.app.latemailsend.data.api.network.AppExecutor
import com.sadmansarar.app.latemailsend.data.api.network.IAppExecutor
import com.sadmansarar.app.latemailsend.data.api.retrofit.LiveDataCallAdapterFactory
import com.sadmansarar.app.latemailsend.data.api.retrofit.WebService
import com.sadmansarar.app.latemailsend.data.db.AppDb
import com.sadmansarar.app.latemailsend.data.db.dao.RepoDao
import com.sadmansarar.app.latemailsend.data.repository.implimentation.IRepoRepository
import com.sadmansarar.app.latemailsend.data.repository.implimentation.PrefRepository
import com.sadmansarar.app.latemailsend.data.repository.interfaces.RepoRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di
 */
@Module(includes = [(ViewModelModule::class)])
internal class AppModule {

    @Singleton
    @Provides
    fun provideApiService(
            gson: Gson,
            stethoInterceptor: StethoInterceptor? = null
    ): WebService {

        val clientBuilder = OkHttpClient.Builder()

        stethoInterceptor?.let { clientBuilder.addNetworkInterceptor(it) }


        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(clientBuilder.build())
                .build()
                .create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDb {
        return Room.databaseBuilder(app, AppDb::class.java, Constants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideAppExecutor(data: IAppExecutor): AppExecutor {
        return data
    }


    @Singleton
    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        val dateTimeParser = DateTimeParser()
        builder.registerTypeAdapter(DateTime::class.java, dateTimeParser)
        builder.registerTypeAdapter(DateTime::class.java, dateTimeParser)
        return builder.create()

    }


    @Singleton
    @Provides
    fun provideStethoInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }


    @Singleton
    @Provides
    fun provideRepoDao(db: AppDb): RepoDao {
        return db.repoDao()
    }

    @Singleton
    @Provides
    fun provideRepoRepository(repo: IRepoRepository): RepoRepository {
        return repo
    }

    @Singleton
    @Provides
    fun providePrefRepository(app: Application): PrefRepository {
        return PrefRepository(app)
    }

}