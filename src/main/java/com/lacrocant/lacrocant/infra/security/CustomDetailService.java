package com.lacrocant.lacrocant.infra.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.lacrocant.lacrocant.domain.admin.Admin;
import com.lacrocant.lacrocant.domain.admin.Role;
import com.lacrocant.lacrocant.infra.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailService implements UserDetailsService {

    @Autowired
	private AdminRepository adminRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final Admin admin = adminRepository.findByUserName(userName);
		if (admin == null) {
			throw new UsernameNotFoundException("User not found to: " + userName);
		}
		return new org.springframework.security.core.userdetails.User(admin.getUserName(), admin.getPassword(),
				admin.getActive(), true, true, true, getAuthority(admin.getRole()));
	}

	private List<GrantedAuthority> getAuthority(Role... adminRoles) {
		final Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();

		roles.add(new SimpleGrantedAuthority(adminRoles[0].toString()));
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}
    
}