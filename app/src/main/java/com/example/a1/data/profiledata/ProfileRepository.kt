import com.example.a1.data.profiledata.ProfileDao
import com.example.a1.data.profiledata.ProfileEntity
import kotlinx.coroutines.flow.Flow

class ProfileRepository(private val profileDao: ProfileDao) {

    fun getProfile(name: String, password: String): Flow<ProfileEntity?> {
        return profileDao.getProfile(name, password)
    }

    suspend fun insertProfile(profile: ProfileEntity) {
        profileDao.insertProfile(profile)
    }

    suspend fun deleteProfile(profile: ProfileEntity) {
        profileDao.deleteProfiles(profile)
    }
}