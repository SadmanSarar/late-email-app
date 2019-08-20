package com.sadmansarar.app.latemailsend

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.facebook.stetho.Stetho
import com.sadmansarar.app.latemailsend.di.AppComponent
import com.sadmansarar.app.latemailsend.di.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject


/**
 * Created by Sadman Sarar on 9/9/17.
 * Application class
 */
class App : Application(), HasActivityInjector, HasSupportFragmentInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var dispatchingAndroidInjectorFragment: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjectorFragment
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    companion object {
        var appComponent: AppComponent? = null
    }


    override fun onCreate() {
        super.onCreate()

        //Instantiate Dagger
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent?.inject(this)

        JodaTimeAndroid.init(this)
        Stetho.initializeWithDefaults(this);
        //Register activity lifeCycle callback listener for automatically injecting every activity
        //that launches
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks() {
            override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
                try {
                    activity?.let { AndroidInjection.inject(activity) }
                } catch (ex: Exception) {
                    Log.w("APP", "No Contributor for the activity")
                }
            }
        })
    }


    abstract class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityStarted(activity: Activity?) {
        }

        override fun onActivityDestroyed(activity: Activity?) {
        }

        override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
        }
    }
}
