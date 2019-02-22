package dk.eboks.app.util

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import dk.eboks.app.BuildConfig

@GlideModule
class EboksAppGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setLogLevel(Log.ERROR)
        if (BuildConfig.BUILD_TYPE.contains("debug", ignoreCase = true)) {
            // builder.setLogLevel(Log.VERBOSE)
        }
    }
}