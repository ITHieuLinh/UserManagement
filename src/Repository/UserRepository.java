package Repository;

import DataAccess.UserDao;

public class UserRepository implements IUserRepository {

    @Override
    public void createNewAccount() {
        UserDao.Instance().createNewAccount();
    }

    @Override
    public void loginSystem() {
        UserDao.Instance().loginSystem();
    }

}
