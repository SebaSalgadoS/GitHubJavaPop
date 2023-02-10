package com.example.githubjavapop.di

import android.widget.ImageView
import com.example.githubjavapop.BuildConfig
import com.example.githubjavapop.data.network.GitHubApiService
import com.example.githubjavapop.data.service.ImageLoader
import com.example.githubjavapop.data.service.ImageLoaderImpl
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiRepoModule {

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApiService(retrofit: Retrofit): GitHubApiService{
        return retrofit.create(GitHubApiService::class.java)
    }

    @Provides
    @Singleton
    fun loadPicasso(): ImageLoader{
        return ImageLoaderImpl()
    }
}