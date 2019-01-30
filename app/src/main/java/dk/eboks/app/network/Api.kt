package dk.eboks.app.network

import com.google.gson.JsonObject
import dk.eboks.app.domain.models.SenderCategory
import dk.eboks.app.domain.models.channel.Channel
import dk.eboks.app.domain.models.channel.ChannelFlags
import dk.eboks.app.domain.models.channel.ekey.EKeyGetMasterkeyResponse
import dk.eboks.app.domain.models.channel.storebox.StoreboxCreditCard
import dk.eboks.app.domain.models.channel.storebox.StoreboxProfile
import dk.eboks.app.domain.models.channel.storebox.StoreboxReceipt
import dk.eboks.app.domain.models.channel.storebox.StoreboxReceiptItem
import dk.eboks.app.domain.models.folder.Folder
import dk.eboks.app.domain.models.folder.FolderPatch
import dk.eboks.app.domain.models.folder.FolderRequest
import dk.eboks.app.domain.models.formreply.ReplyForm
import dk.eboks.app.domain.models.home.HomeContent
import dk.eboks.app.domain.models.login.ActivationDevice
import dk.eboks.app.domain.models.login.SharedUser
import dk.eboks.app.domain.models.login.User
import dk.eboks.app.domain.models.message.Message
import dk.eboks.app.domain.models.message.MessagePatch
import dk.eboks.app.domain.models.message.StorageInfo
import dk.eboks.app.domain.models.protocol.AliasBody
import dk.eboks.app.domain.models.sender.CollectionContainer
import dk.eboks.app.domain.models.sender.Registrations
import dk.eboks.app.domain.models.sender.Segment
import dk.eboks.app.domain.models.sender.Sender
import dk.eboks.app.domain.models.shared.BooleanReply
import dk.eboks.app.domain.models.shared.Link
import dk.eboks.app.domain.models.shared.ResourceLink
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by bison on 20-05-2017.
 */

interface Api {
    // login Mox
    /*
    @FormUrlEncoded
    @POST("http://test401-oauth-dk.internal.e-boks.com/1/connect/token") fun getToken(@FieldMap bodyMap: Map<String, String>): Call<AccessToken>
    */

    // mobile access
    @PUT("user/current/device")
    fun activateDevice(@Body device: ActivationDevice): Call<Void>

    // resources
    @GET("resources/links")
    fun getResourceLinks(): Call<List<ResourceLink>>

    //folders
    @POST("mail/folders")
    fun createFolder(@Body folderRequest: FolderRequest): Call<Void>

    @PATCH("mail/folders/{folderid}")
    fun editFolder(@Path("folderid") folderId: Int, @Body folderRequest: FolderRequest): Call<Void>

    @DELETE("mail/folders/{folderid}")
    fun deleteFolder(@Path("folderid") folderId: Int): Call<Void>

    // user
    @GET("user/{identity}/exists")
    fun checkUserEmail(@Path("identity") identity: String): Call<BooleanReply>

    @GET("user/identity/{identity}")
    fun checkUserIdentity(@Path("identity") cpr: String): Call<BooleanReply>

    @POST("user/profile")
    fun createUserProfile(@Body user: JsonObject): Call<Any>

    @POST("user/{nationality}/{email}/resetpassword")
    fun resetPassword(@Path("nationality") nationality: String, @Path("email") email: String): Call<Void>

    @POST("user/current/migrate/{targetUserId}")
    fun migrateUser(@Path("targetUserId") targetUserId: String): Call<Void>

    @GET("user/profile")
    fun getUserProfile(): Call<User>

    @PATCH("user/profile")
    fun updateProfile(@Body user: JsonObject): Call<Any>

    @POST("user/current/email/{email}/verify")
    fun verifyEmail(@Path("email") email: String): Call<Any>

    @POST("user/current/mobile/{mobile}/verify")
    fun verifyMobile(@Path("mobile") mobile: String): Call<Void>

    @POST("user/current/mobile/{mobile}/verify/{activationCode}")
    fun confirmMobile(@Path("mobile") mobile: String, @Path("activationCode") activationCode: String): Call<Void>

    // @GET("regions") fun getRegions() : Call<List<Region>>
    @GET("mail/folders/selected")
    fun getMailCategories(@Query("userId") userId: Int? = null): Call<List<Folder>>

    @GET("mail/folders")
    fun getFolders(@Query("userId") userId: Int?): Call<List<Folder>>

    @GET("mail/folders/{id}/messages")
    fun getMessages(@Path("id") id: Int, @Query("userId") userId: Int?, @Query("offset") offset: Int? = null, @Query("limit") limit: Int? = null, @Query("acceptprivateterms") terms: Boolean? = null): Call<List<Message>>

    @GET("mail/messages/senders/{id}")
    fun getMessagesBySender(@Path("id") id: Long, @Query("userId") userId: Int?, @Query("offset") offset: Int? = null, @Query("limit") limit: Int? = null, @Query("acceptprivateterms") terms: Boolean? = null): Call<List<Message>>

    @GET("mail/folders/{folderId}/messages/{id}")
    fun getMessage(@Path("id") id: String, @Path("folderId") folderId: Int, @Query("userId") userId: Int?, @Query("receipt") receipt: Boolean? = null, @Query("acceptprivateterms") terms: Boolean? = null): Call<Message>

    @GET("mail/senders")
    fun getSenders(@Query("userId") userId: Int?): Call<List<Sender>>

    // edit message / document/message operations
    @POST("mail/folders/{folderId}/messages/{messageId}")
    fun updateMessage(@Path("folderId") folderId: Int, @Path("messageId") messageId: String, @Body body: MessagePatch, @Query("userId") userId: Int?): Call<Void>

    //edit folder
    @PATCH("mail/folders/{folderId}")
    fun updateFolder(@Path("folderId") folderId: Int, @Body body: FolderPatch): Call<Void>

    // delete message
    @DELETE("mail/folders/{folderId}/messages/{messageId}")
    fun deleteMessage(@Path("folderId") folderId: Int, @Path("messageId") messageId: String): Call<Void>

    // get types of messages, used to be by folder type but now its just a couple of hardcoded endpoints
    @GET("mail/messages/highlights")
    fun getHighlights(@Query("acceptprivateterms") terms: Boolean? = null): Call<List<Message>>

    @GET("mail/messages/latest")
    fun getLatest(@Query("acceptprivateterms") terms: Boolean? = null): Call<List<Message>>

    @GET("mail/messages/unread")
    fun getUnread(@Query("acceptprivateterms") terms: Boolean? = null): Call<List<Message>>

    @GET("mail/messages/uploads")
    fun getUploads(@Query("offset") offset: Int? = null, @Query("limit") limit: Int? = null): Call<List<Message>>

    @GET("mail/storage")
    fun getStorageInfo(): Call<StorageInfo>

    @GET("/groups/senders/{senderId}/link")
    fun getSenderRegistrationLink(@Path("senderId") senderId: Long) : Single<Any>

    // sign
    @GET("mail/folders/{folderId}/messages/{id}/sign/link")
    fun getSignLink(
            @Path("id") id: String,
            @Path("folderId") folderId: Int,
            @Query("callback_cancel") callbackCancel: String,
            @Query("callback_success") callbackSuccess: String,
            @Query("callback_error") callbackError: String
    ): Call<Link>

    // reply forms
    @GET("mail/folders/{folderId}/messages/{id}/reply")
    fun getMessageReplyForm(@Path("id") id: String, @Path("folderId") folderId: Int): Call<ReplyForm>

    @PATCH("mail/folders/{folderId}/messages/{id}/reply")
    fun submitMessageReplyForm(@Path("id") id: String, @Path("folderId") folderId: Int, @Body body: ReplyForm): Call<Any>

    // channels
    @GET("channels")
    fun getChannels(): Call<MutableList<Channel>>

    @GET("channels?pinned=true")
    fun getChannelsPinned(): Call<MutableList<Channel>>

    @GET("channels?installed=true")
    fun getChannelsInstalled(): Call<MutableList<Channel>>

    @GET("channels/{id}")
    fun getChannel(@Path("id") id: Int): Call<Channel>

    @PUT("channels/{id}/install")
    fun installChannel(@Path("id") id: Int): Call<Void>

    @DELETE("channels/{id}")
    fun uninstallChannel(@Path("id") id: Int): Call<Void>

    @GET("channels/{id}/content/home")
    fun getChannelHomeContent(@Path("id") id: Long): Call<HomeContent>

    @PATCH("channels/{id}")
    fun updateChannelFlags(@Path("id") id: Int, @Body body: ChannelFlags): Call<Void>

    @GET("channels/{id}/content/open")
    fun getChannelHTMLContent(@Path("id") id: Int, @Query("access_token") accessToken: String): Call<String>

    // storebox specific
    @GET("channels/storebox/receipts")
    fun getStoreboxReceipts(): Call<List<StoreboxReceiptItem>>

    @GET("channels/storebox/receipts/{id}")
    fun getStoreboxReceipt(@Path("id") id: String): Call<StoreboxReceipt>

    @DELETE("channels/storebox/receipts/{id}")
    fun deleteStoreboxReceipt(@Path("id") id: String): Call<Void>

    @POST("channels/storebox/user/signup")
    fun createStoreboxAccount(): Call<Void>

    @POST("channels/storebox/user/signup/link")
    fun postLinkStorebox(@Body bodyMap: Map<String, String>): Call<Any>

    @POST("channels/storebox/user/signup/link/activate")
    fun postActivateStorebox(@Body bodyMap: Map<String, String>): Call<Any>

    @GET("channels/storebox/user/profile")
    fun getStoreboxProfile(): Call<StoreboxProfile>

    @PUT("channels/storebox/user/profile")
    fun putStoreboxProfile(@Body profile: StoreboxProfile): Call<Void>

    @GET("channels/storebox/user/cards/card/link")
    fun getStoreboxCardLink(@Query("callback_success") callbackSuccess: String, @Query("callback_error") callbackError: String): Call<Link>

    @GET("channels/storebox/user/cards")
    fun getStoreboxCreditCards(): Call<MutableList<StoreboxCreditCard>>

    @DELETE("channels/storebox/user/cards/{cardId}")
    fun deleteStoreboxCreditCard(@Path("cardId") id: String): Call<Void>

    @DELETE("channels/storebox/user")
    fun deleteStoreboxAccountLink(): Call<Void>

    @POST("channels/storebox/receipts/{id}/saveToFolder/{folderId}")
    fun saveStoreboxReceipt(@Path("id") id: String, @Path("folderId") folderId: Int): Call<Void>

    // groups
    @GET("groups/registrations")
    fun getRegistrations(): Call<Registrations> // get all my registrations

    @GET("groups/registrations/pending/collections")
    fun getPendingRegistrations(): Call<List<CollectionContainer>>

    @GET("groups/collections")
    fun getCollections(): Call<List<CollectionContainer>> // for the Senders-landing page

    @GET("groups/segments/type/{segment}/categories")
    fun getSenderCategories(@Path("segment") segment: String): Call<List<SenderCategory>> // private or public

    @GET("groups/{id}")
    fun getSenders(@Path("id") categoryId: Long): Call<SenderCategory>

    @GET("groups/senders")
    fun searchSenders(@Query("searchText") searchText: String): Call<List<Sender>>

    @GET("groups/segments/{id}")
    fun getSegmentDetail(@Path("id") segmentId: Long): Call<Segment>               // segment detail

    @GET("groups/senders/{id}")
    fun getSenderDetail(@Path("id") senderId: Long): Call<Sender>

    @GET("groups/segments")
    fun getSegments(): Call<List<Segment>>

    // register senders
    @PUT("groups/segments/{segId}")
    fun registerSegment(@Path("segId") segmentId: Long): Call<Any>              // TODO should we have SegmentID or SegmentType?

    @PUT("groups/segments/{segType}")
    fun registerSegment(@Path("segType") segmentType: String): Call<Any>      // TODO should we have SegmentType or SegmentID?

    @PUT("groups/senders/{id}")
    fun registerSender(@Path("id") senderId: Long): Call<Any>

    @PUT("groups/senders/{sId}/sendergroups/{gId}")
    fun registerSenderGroup(@Path("sId") senderId: Long, @Path("gId") groupId: Long, @Body aliasRegistrations: AliasBody): Call<Any>

    // un-register senders
    @DELETE("groups/segments/{segId}")
    fun unregisterSegment(@Path("segId") segmentId: Long): Call<Any>         // TODO should we have SegmentID or SegmentType?

    @DELETE("groups/segments/{segType}")
    fun unregisterSegment(@Path("segType") segmentType: String): Call<Any> // TODO should we have SegmentType or SegmentID?

    @DELETE("groups/senders/{id}")
    fun unregisterSender(@Path("id") senderId: Long): Call<Any>

    @DELETE("groups/senders/{sId}/sendergroups/{gId}/alias/{aId}")
    fun unregisterSenderGroup(@Path("sId") senderId: Long, @Path("gId") groupId: Long): Call<Any> // bodyless version // TODO check URL!!!
    //    @DELETE("groups/senders/{sId}/sendergroups/{gId}") fun unregisterSenderGroup(@Path("sId") senderId : Long, @Path("gId") groupId : Long, @Body aliasRegistrations : AliasBody) : Call<Any> // TODO: This needs to be a PATCH or POST or be bodyless as the one above

    // E-Key Stuff

    @GET("channels/ekey/vault")
    fun keyVaultGet(@Query("signaturetime") signaturetime: String, @Query("signature") signature: String): Call<String>

    @PUT("channels/ekey/vault")
    fun keyVaultSet(@Body vault: String, @Query("signaturetime") signaturetime: String, @Query("signature") signature: String): Call<ResponseBody>

    @DELETE("channels/ekey/vault")
    fun keyVaultDelete(@Query("signaturetime") signaturetime: String, @Query("signature") signature: String): Call<ResponseBody>

    @GET("channels/ekey/masterkey")
    fun masterKeyGet(): Call<EKeyGetMasterkeyResponse>

    fun masterKeySet(@Body obj: JsonObject): Call<ResponseBody>

    @DELETE("channels/ekey/masterkey")
    fun masterKeyDelete(): Call<ResponseBody>

    // shares
    @GET("mail/shares/all")
    fun getAllShares(): Call<List<SharedUser>>

}
