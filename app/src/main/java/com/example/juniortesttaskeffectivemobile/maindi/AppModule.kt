package com.example.juniortesttaskeffectivemobile.maindi

import android.content.Context
import com.example.data.mappers.fromCourseDtoToCourse.MapperFromCourseDtoToCourse
import com.example.data.mappers.fromCourseDtoToCourse.MapperFromCourseDtoToCourseImpl
import com.example.data.repository.AppRepositoryImpl
import com.example.data.retrofit.Api
import com.example.data.retrofit.BASE_URL
import com.example.data.retrofit.MockInterceptor
import com.example.data.retrofit.RetrofitClient
import com.example.domain.AppRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(MockInterceptor(context))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRetrofitClient(context: Context): RetrofitClient {
        return RetrofitClient(context)
    }

    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun provideMapperFromCourseDtoToCourse(): MapperFromCourseDtoToCourse {
        return MapperFromCourseDtoToCourseImpl()
    }

    @Provides
    fun provideRepository(
        context: Context,
        mapperFromCourseDtoToCourse: MapperFromCourseDtoToCourse
    ): AppRepository {
        return AppRepositoryImpl(
            RetrofitClient.getInstance(context).allPersonApi,
            mapperFromCourseDtoToCourse
        )
    }
}
