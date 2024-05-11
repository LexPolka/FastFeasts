
import com.example.a1.data.profiledata.ProfileDao
import com.example.a1.data.profiledata.ProfileEntity
import kotlinx.coroutines.flow.Flow

class ProfileOfflineRepository(private val profileDao: ProfileDao) : ProfileRepository {

    override fun getProfile(email: String, password: String): Flow<ProfileEntity?> {
        return profileDao.getProfile(email, password)
    }

    override suspend fun insertProfile(profile: ProfileEntity) {
        profileDao.insertProfile(profile)
    }

    override suspend fun deleteProfile(profile: ProfileEntity) {
        profileDao.deleteProfiles(profile)
    }
}