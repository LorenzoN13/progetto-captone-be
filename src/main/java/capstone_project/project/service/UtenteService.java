package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Utente;
import capstone_project.project.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder encoder;

    public Utente findByUsername(String username) throws NotFoundException {
        Optional<Utente> optionalUser = utenteRepository.findByUsername(username);
        if(optionalUser.isEmpty())throw new NotFoundException("Utente not found");
        return optionalUser.get();
    }

    public Page<Utente> getAllUsers(Pageable pageable) {
        return utenteRepository.findAll(pageable);
    }

    public Utente getUserById(int id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(()-> new NotFoundException("Utente with id= " + id + " was not found"));
    }
    public Utente saveUtente(RegisterRequest registerRequest) {
        User x = new User();
        x.setUsername(registerRequest.getUsername());
        x.setEmail(registerRequest.getEmail());
        x.setPassword(encoder.encode(registerRequest.getPassword()));
        x.setFirstName(registerRequest.getFirstName());
        x.setLastName(registerRequest.getLastName());
        x.setRoles(Set.of(Role.USER));
        return userRepository.save(x);
    }

    public User updateUser(long id, UserRequest userRequest) throws NotFoundException {
        User x = getUserById(id);
        x.setUsername(userRequest.getUsername());
        x.setEmail(userRequest.getEmail());
        x.setFirstName(userRequest.getFirstName());
        x.setLastName(userRequest.getLastName());

        return userRepository.save(x);
    }

    public void deleteUser(Long id) throws NotFoundException {
        User x = getUserById(id);
        userRepository.delete(x);
    }

    public User uploadAvatar(long id, String url) throws NotFoundException{
        User x = getUserById(id);
        x.setAvatar(url);
        return userRepository.save(x);
    }

    public void updateUserToAdmin(Long userId) throws NotFoundException, AlreadyAdminException {
        User user = getUserById(userId);
        if(user.getRoles().contains(Role.ADMIN)) throw new AlreadyAdminException("Already admin");
        user.addRole(Role.ADMIN);
        userRepository.save(user);
    }
}
