package com.devjavu.standardProject.configuration;

import com.devjavu.standardProject.entity.standardEntity.Role;
import com.devjavu.standardProject.entity.standardEntity.User;
import com.devjavu.standardProject.enums.ProjectRoles;
import com.devjavu.standardProject.enums.StandardRoles;
import com.devjavu.standardProject.repository.standardRepo.RoleRepository;
import com.devjavu.standardProject.repository.standardRepo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    // Trong file ApplicationInitConfig.java

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, InitService initService) {
        return args -> {
            initService.initData(userRepository);
        };
    }

    // Tạo một inner class hoặc một Service riêng
    @Service
    @RequiredArgsConstructor
    class InitService {
        final PasswordEncoder passwordEncoder;
        final RoleRepository roleRepository;

        @Transactional // <--- Quan trọng nhất ở đây
        public void initData(UserRepository userRepository) {
            if (userRepository.findByUsername("ChuThuVien").isEmpty()) {
                User user = User.builder()
                        .username("ChuThuVien")
                        .password(passwordEncoder.encode("admin"))
                        .status("ACTIVE")
                        .failedLoginAttempts(0)
                        .build();

                String[] INIT_ROLES = {
                        StandardRoles.USER.name(),
                        ProjectRoles.DOC_GIA.name(),
                        ProjectRoles.NHAN_VIEN.name(),
                        ProjectRoles.CHU_THU_VIEN.name()
                };

                Set<Role> roles = new HashSet<>(roleRepository.findAllById(List.of(INIT_ROLES)));
                user.setRoles(roles);
                userRepository.save(user);

                log.warn("Chu thu vien user created!");
            }
        }
    }
}
