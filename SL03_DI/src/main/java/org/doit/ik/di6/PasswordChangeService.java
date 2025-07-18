package org.doit.ik.di6;

public class PasswordChangeService {
   
   private UserRepository userRepository;

   public PasswordChangeService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public void changePassword(String userId, String oldPw, String newPw) {
      User user = userRepository.findUserById(userId);
      if (user == null)
         throw new UserNotFoundException();

      user.changePassword(oldPw, newPw);
   }

}