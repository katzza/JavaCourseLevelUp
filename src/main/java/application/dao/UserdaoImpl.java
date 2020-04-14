package application.dao;

import application.domain.UserAddressEntity;
import application.domain.UserEntity;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserdaoImpl extends AbstractDao implements UserDao {
    public UserdaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public UserEntity createUser(String name, String lastName, String passport, Collection<String> addresses) {
        return runInTransaction(session -> {
            UserEntity user = new UserEntity();
            user.setName(name);
            user.setLastName(lastName);
            user.setPassport(passport);

            // Coll <String ->UserAddressEntity
            List<UserAddressEntity> addressEntities = addresses.stream()
                    .map(address -> {
                        UserAddressEntity addressEntity = new UserAddressEntity();
                        addressEntity.setAddress(address);
              //          addressEntity.setUser(user);
                        return addressEntity;
                    })
                    .collect(Collectors.toList());

            user.setAddresses(addressEntities);
            session.persist(user);
            return user;
        });
    }
}
