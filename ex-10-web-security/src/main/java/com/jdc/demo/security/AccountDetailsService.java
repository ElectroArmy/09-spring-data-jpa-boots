package com.jdc.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.jdc.demo.service.repo.AccountRepo;

@Service
public class AccountDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepo repo;

	private InMemoryUserDetailsManager adminUserDetails;
	
	public AccountDetailsService() {
		adminUserDetails = new InMemoryUserDetailsManager();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		var admin = adminUserDetails.loadUserByUsername(username);
		
		if (null != admin) {
			return admin;
		}
		
		return repo.findOneByLoginId(username)
				.map(a -> User.builder()
						.username(username)
						.password(a.getPassword())
						.accountExpired(a.isExpired())
						.accountLocked(!a.isActivated())
						.authorities(a.getRole().name())
						.build()).orElseThrow();
	}

}
