package kosta.mvc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scrap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scrapId;
	
	@ManyToOne
	@JoinColumn(name="recruit_id")
	private Recruit recruit;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
}
