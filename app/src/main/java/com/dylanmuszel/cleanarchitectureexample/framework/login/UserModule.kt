package com.dylanmuszel.cleanarchitectureexample.framework.login

import com.dylanmuszel.cleanarchitectureexample.framework.core.network.ApiInstance
import com.dylanmuszel.cleanarchitectureexample.framework.core.network.ConnectivityInfo
import com.dylanmuszel.cleanarchitectureexample.framework.core.sharedpreferences.SharedPreferencesManager
import com.dylanmuszel.data.UserSession
import com.dylanmuszel.data.UserSource
import dagger.Module
import dagger.Provides

@Module
object UserModule {

    @JvmStatic
    @Provides
    fun providesUserService(apiInstance: ApiInstance) = apiInstance.getService(UserService::class.java)

    @JvmStatic
    @Provides
    fun providesUserSource(
        userService: UserService,
        connectivityInfo: ConnectivityInfo
    ): UserSource = UserNetworkSource(userService, connectivityInfo)

    @JvmStatic
    @Provides
    fun providesUserSession(sharedPreferencesManager: SharedPreferencesManager): UserSession =
        UserSharedPreferencesSession(sharedPreferencesManager)
}
