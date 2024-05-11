import com.example.a1.data.profiledata.ProfileDao
import com.example.a1.data.profiledata.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(email: String, password: String): Flow<ProfileEntity?>

    suspend fun insertProfile(profile: ProfileEntity)

    suspend fun deleteProfile(profile: ProfileEntity)
}