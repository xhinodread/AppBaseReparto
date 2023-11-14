package com.example.appbase.di

import android.util.Log
import com.example.appbase.core.ResultCallAdapterFactory
import com.example.appbase.domain.repository.LoginRepository
import com.example.appbase.domain.repository.SeguimientoRepository
import com.example.appbase.network.repository.LoginRepositoryImpl
import com.example.appbase.network.repository.SeguimientoRepositoryImpl
import com.example.appbase.network.service.SeguimientoService
import com.example.appbase.utils.Const.KEY_API
import com.example.appbase.utils.Const.KEY_API2
import com.example.appbase.utils.Const.PAGE_SIZE
import com.example.appbase.utils.Const.WEB_API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Named
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import okio.Buffer
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    @Named("WEB_API")
    fun provideWebAPI(): String = WEB_API

    @Provides
    @Named("KEY_API")
    fun provideKeyAPI(): String = KEY_API

    @Provides
    @Named("KEY_API2")
    fun provideKeyAPI2(): String = KEY_API2

    @Singleton
    @Provides
    @Named("PAGE_SIZE")
    fun providePageSize(): Int = PAGE_SIZE
    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("WEB_API") webAPI: String,
    ): Retrofit {
        val client = OkHttpClient
           .Builder()
           .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .ignoreAllSSLErrors()
            .build()

        return Retrofit.Builder()
            .baseUrl(webAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .client(client)
            .build()
    }
    @Singleton
    @Provides
    fun provideSeguimientoService(
        retrofit: Retrofit
    ): SeguimientoService = retrofit.create(SeguimientoService::class.java)
    @Singleton
    @Provides
    fun provideSeguimientoRepository(
        seguimiento: SeguimientoService,
        @Named("KEY_API2") keyApi2: String,
        @Named("PAGE_SIZE") pageSize: Int,
    ): SeguimientoRepository = SeguimientoRepositoryImpl(
        seguimientoService = seguimiento,
        apiKey2 = keyApi2
    )

    @Singleton
    @Provides
    fun provideLoginRepository(
        seguimiento: SeguimientoService,
        @Named("KEY_API2") keyApi2: String,
    ): LoginRepository = LoginRepositoryImpl(
                            seguimientoService = seguimiento,
                        )

    fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
        val naiveTrustManager = object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        }

        val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
            val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
            init(null, trustAllCerts, SecureRandom())
        }.socketFactory

        sslSocketFactory(insecureSocketFactory, naiveTrustManager)
        hostnameVerifier(HostnameVerifier { _, _ -> true })
        return this
    }

    fun OkHttpClient.Builder.estadoRed(): OkHttpClient.Builder{
        return this
    }

}

/****
class MyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addContentTypeHeader()
                .addUserAgentHeader()
                .addBunqHeaders(chain.request())
                .build()
        )
    }

    private fun Request.Builder.addSignatureHeader(request: Request) =
        header("X-Client-Signature", signer.sign(request.getBodyAsString()))

    private fun Request.getBodyAsString(): String {
        val requestCopy = this.newBuilder().build()
        val buffer = Buffer()
        requestCopy.body?.writeTo(buffer)
        return buffer.readUtf8()
    }
}
        *****/