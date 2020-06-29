package kosta.mvc.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kosta.mvc.domain.Members;
import kosta.mvc.repository.MembersRepository;

@Component
public class MemberAuthenticationProvider  implements AuthenticationProvider {
	@Autowired
	private MembersRepository memberRepository;
	
	private PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private final String[] roles = {"ROLE_USER", "ROLE_COMPANY", "ROLE_ADMIN"};
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println(authentication.getName());
		
		List<Members> members = memberRepository.findByMemberEmail(authentication.getName());
		Members member = null;
		
		for(Members m : members) {
			if(m.getMemberStatus() == 1 || m.getMemberStatus() == 2 || m.getMemberStatus() == 3) member = m;
		}
		
		if(member == null) {
			throw new UsernameNotFoundException("정보 확인 바람"); //아이디에 해당하는 정보 없는 경우
		}
		
		if(!encoder.matches(authentication.getCredentials().toString(), member.getMemberPassword())) {
			throw new UsernameNotFoundException("정보 확인 바람");  //비밀번호 불일치
		}
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		
		//일반회원 1, 기업 2, 관리자 3
		for(long i = member.getMemberStatus(); i > 0; i--) {
			authList.add(new SimpleGrantedAuthority(roles[(int)(i - 1)]));
		}
		System.out.println(authList);
		
		return new UsernamePasswordAuthenticationToken(member, null, authList);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
