package dk.eboks.app.domain.config

import dk.eboks.app.BuildConfig
import dk.eboks.app.R
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.presentation.ui.components.start.login.LoginComponentFragment
import dk.eboks.app.presentation.ui.components.start.login.providers.bankidno.BankIdNOComponentFragment
import dk.eboks.app.presentation.ui.components.start.login.providers.bankidse.BankIdSEComponentFragment
import dk.eboks.app.presentation.ui.components.start.login.providers.idporten.IdPortenComponentFragment
import dk.eboks.app.presentation.ui.components.start.login.providers.nemid.NemIdComponentFragment
import java.net.URL

// TODO this stuff should be downloaded from a url (on request of the customer) so that the app only contains
// one harded coded url ya'll
/**
 * Created by bison on 07/12/17.
 *
 * This class should not be written to at runtime, it presents a basic configuration for each of the
 * app flavors (DK, SE, NO)
 *
 */
object Config {
    private val danish : Mode = Mode(
            urlPrefix = "DA-DK",
            countryCode = "DK",
            cprLength = 10,
            demoVideo = "https://youtu.be/8OmF6uHxfWU",

            demo = Environments(
                apiUrl = "https://demo-mobile-api-dk.internal.e-boks.com/2/",
                authUrl = "https://demo-oauth-dk.internal.e-boks.com/1/connect/token",
                kspUrl = "https://demo-m.e-boks.dk/app/logon.aspx?logontype="
            ),
            test = Environments(
                apiUrl = "http://test401-mobile-api-dk.internal.e-boks.com/2/",
                authUrl = "http://test401-oauth-dk.internal.e-boks.com/1/connect/token",
                kspUrl = "https://demo-m.e-boks.dk/app/logon.aspx?logontype="
            ),
            customTranslationUrl = "https://m.e-boks.dk/app/resources/android/eboks.android.3.3.0.json",
            alternativeLoginProviders = listOf("nemid")
    )

    private val norwegian : Mode = Mode(
            urlPrefix = "NB-NO",
            countryCode = "NO",
            cprLength = 11,
            demoVideo = "https://youtu.be/8OmF6uHxfWU",


            demo = Environments(
                    apiUrl = "https://demo-mobile-api-dk.internal.e-boks.com/2/",
                    authUrl = "https://demo-oauth-dk.internal.e-boks.com/1/connect/token",
                    kspUrl = "https://demo-m.e-boks.dk/app/logon.aspx?logontype="
            ),
            test = Environments(
                    apiUrl = "http://test401-mobile-api-dk.internal.e-boks.com/2/",
                    authUrl = "http://test401-oauth-dk.internal.e-boks.com/1/connect/token",
                    kspUrl = "https://demo-m.e-boks.dk/app/logon.aspx?logontype="
            ),
            customTranslationUrl = "https://m.e-boks.no/app/resources/android/eboks.android.3.5.0.json",
            alternativeLoginProviders = listOf("idporten", "bankid_no")
    )

    private val swedish : Mode = Mode(
            urlPrefix = "SV-SE",
            countryCode = "SE",
            cprLength = 12,
            demoVideo = "https://youtu.be/8OmF6uHxfWU",

            demo = Environments(
                    apiUrl = "https://demo-mobile-api-dk.internal.e-boks.com/2/",
                    authUrl = "https://demo-oauth-dk.internal.e-boks.com/1/connect/token",
                    kspUrl = "https://demo-m.e-boks.dk/app/logon.aspx?logontype="
            ),
            test = Environments(
                    apiUrl = "http://test401-mobile-api-dk.internal.e-boks.com/2/",
                    authUrl = "http://test401-oauth-dk.internal.e-boks.com/1/connect/token",
                    kspUrl = "https://demo-m.e-boks.dk/app/logon.aspx?logontype="
            ),
            customTranslationUrl = "https://m.e-boks.se/app/resources/android/eboks.android.3.5.0.json",
            alternativeLoginProviders = listOf("bankid_se")
    )

    val loginProviders: Map<String, LoginProvider> = mapOf(
            "email" to LoginProvider(
                    id = "email",
                    name = "Email",
                    onlyVerified = false,
                    icon = -1,
                    description = null,
                    fragmentClass = LoginComponentFragment::class.java,
                    fallbackProvider = "email"
            ),
            "cpr" to LoginProvider(
                    id = "cpr",
                    name = "Social Security Number",
                    onlyVerified = false,
                    icon = -1,
                    description = null,
                    fragmentClass = LoginComponentFragment::class.java,
                    fallbackProvider = "cpr"
            ),
            "nemid" to LoginProvider(
                    id = "nemid",
                    name = "NemID",
                    onlyVerified = true,
                    icon = R.drawable.black,
                    description = Translation.loginproviders.nemidDescription,
                    fragmentClass = NemIdComponentFragment::class.java,
                    fallbackProvider = "cpr"
            ),
            "idporten" to LoginProvider(
                    id = "idporten",
                    name = "ID-Porten",
                    onlyVerified = true,
                    icon = R.drawable.ic_idporten,
                    description = Translation.loginproviders.idPortenDescription,
                    fragmentClass = IdPortenComponentFragment::class.java,
                    fallbackProvider = "cpr"
            ),
            "bankid_se" to LoginProvider(
                    id = "bankid_se",
                    name = "Bank ID",
                    onlyVerified = true,
                    icon = R.drawable.ic_bankid,
                    description = Translation.loginproviders.bankSeDescription,
                    fragmentClass = BankIdSEComponentFragment::class.java,
                    fallbackProvider = "cpr"
            ),
            "bankid_no" to LoginProvider(
                    id = "bankid_no",
                    name = "Bank ID",
                    onlyVerified = true,
                    icon = R.drawable.ic_bankid,
                    description = Translation.loginproviders.bankNoDescription,
                    fragmentClass = BankIdNOComponentFragment::class.java,
                    fallbackProvider = "cpr"
            )
    )

    fun getLoginProvider(id : String) : LoginProvider?
    {
        return loginProviders[id]
    }

    fun getAlternativeLoginProviders() : MutableList<LoginProvider>
    {
        val providers : MutableList<LoginProvider> = ArrayList()
        for(provider_id in currentMode.alternativeLoginProviders)
        {
            getLoginProvider(provider_id)?.let { provider ->
                providers.add(provider)
            }
        }
        return providers
    }

    fun isDK() : Boolean { return currentMode == danish }
    fun isNO() : Boolean { return currentMode == norwegian }
    fun isSE() : Boolean { return currentMode == swedish }

    fun changeConfig(name : String)
    {
        when(name)
        {
            "danish" -> {
                Config.currentMode = Config.danish
            }
            "swedish" -> {
                Config.currentMode = Config.swedish
            }
            "norwegian" -> {
                Config.currentMode = Config.norwegian
            }
            else -> throw(IllegalStateException("Configuration mode ${name} is invalid. Use danish, swedish or norwegian"))
        }
    }

    fun getCurrentConfigName() : String
    {
        when(currentMode)
        {
            danish -> {
                return "danish"
            }
            swedish -> {
                return "swedish"
            }
            norwegian -> {
                return "norwegian"
            }
            else -> return "unknown"
        }
    }

    fun getLogoResourceId() : Int
    {
        when(currentMode)
        {
            danish -> {
                return R.drawable.eboks_dk_logo
            }
            swedish -> {
                return R.drawable.eboks_se_logo
            }
            norwegian -> {
                return R.drawable.eboks_no_logo
            }
            else -> return R.drawable.eboks_dk_logo
        }
    }

    fun getApiUrl() : String
    {
        return currentMode.environment?.apiUrl ?: throw(IllegalStateException("No api url selected"))
    }

    fun getAuthUrl() : String
    {
        return currentMode.environment?.authUrl ?: throw(IllegalStateException("No auth url selected"))
    }

    fun getApiHost() : String
    {
        return URL(getApiUrl()).host
    }

    fun getAuthHost() : String
    {
        return URL(getApiUrl()).host
    }

    lateinit var currentMode : Mode


    init {
        when(BuildConfig.mode)
        {
            "danish" -> {
                currentMode = danish
            }
            "swedish" -> {
                currentMode = swedish
            }
            "norwegian" -> {
                currentMode = norwegian
            }
            else -> throw(IllegalStateException("Configuration mode ${BuildConfig.mode} is invalid. Use danish, swedish or norwegian"))
        }
        currentMode.environment = if(BuildConfig.DEBUG) currentMode.demo else currentMode.production
        if(currentMode.environment == null)
            throw(IllegalStateException("currentMode.environment is not set!!"))

    }
}