
package com.ui.product.zokudo.services.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ui.product.zokudo.util.CommonUtil;

@Service
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> set = new HashSet<SimpleGrantedAuthority>();
		String roles = CommonUtil.convertToEmptyIfNull(this.authorities);
		if (!roles.equals("")) {
			String[] rolesArr = roles.split(",");
			for (String eachRole : rolesArr) {
				set.add(new SimpleGrantedAuthority(eachRole));
			}
		}
		return set;
	}

	@Override
	public String getPassword() {
		return CommonUtil.convertToEmptyIfNull(this.password);
	}

	@Override
	public String getUsername() {

		return CommonUtil.convertToEmptyIfNull(this.userName);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		return !CommonUtil.convertToEmptyIfNull(this.userName).equals("") ? this.userName.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof UserDetailsImpl) {
				UserDetailsImpl userDetailsImpl = (UserDetailsImpl) obj;
				return this.hashCode() == userDetailsImpl.hashCode();
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
}
