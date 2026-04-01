import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserService {
    private final List<RegisteredUsers> registeredUsers;
    public UserService() {
        this.registeredUsers = new ArrayList<>();
    }

    public void addUser(RegisteredUsers newUser) {
        registeredUsers.add(newUser);
    }

    public RegisteredUsers getUserByEmail(String email) {
        for (RegisteredUsers user : registeredUsers) {
            if (user.getEmailAddress().equals(email)) {
                return user;
            }
        }
        return null;
    }
    public boolean removeUserByEmail(String email) {
        if (registeredUsers.isEmpty()) return false;
        
        Iterator<RegisteredUsers> iterator = registeredUsers.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(email)) {
                iterator.remove();
                return true; 
            }
        }
        return false; 
    }

    public List<RegisteredUsers> getAllUsers() {
        return new ArrayList<>(registeredUsers); 
    }

    public boolean updateUser(String targetEmail, 
                            String newFullName, String newEmail, String newDob,
                            String newProvider, String newExpiry, String newUserType,
                            Long newCardNum, Integer newCvv) {
        RegisteredUsers targetUser = getUserByEmail(targetEmail);
        if (targetUser == null) return false;

        if (newFullName != null && !newFullName.isEmpty()) targetUser.setFullName(newFullName);
        if (newEmail != null && !newEmail.isEmpty()) targetUser.setEmailAddress(newEmail);
        if (newDob != null && !newDob.isEmpty()) targetUser.setDateOfBirth(newDob);
        if (newProvider != null && !newProvider.isEmpty()) targetUser.setCardProvider(newProvider);
        if (newExpiry != null && !newExpiry.isEmpty()) targetUser.setCardExpiryDate(newExpiry);
        if (newUserType != null && !newUserType.isEmpty()) targetUser.setUserType(newUserType);
        if (newCardNum != null) targetUser.setCardNumber(newCardNum);
        if (newCvv != null) targetUser.setCvv(newCvv);

        return true; 
    }
}

