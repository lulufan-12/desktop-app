package application.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "Role")
@RequiredArgsConstructor
@Table(name = "en_role")
public class Role extends DefaultModel {
	
	@Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "se_role_id", allocationSize = 1, sequenceName = "se_role_id")
    private Long id;
	
	@Column(length = 50, nullable = false, unique = true)
	String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
		name = "re_user_role",
		joinColumns = { @JoinColumn(name = "role_id", nullable = false) },
		inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) }
	)
    private Set<User> users = new HashSet<>();
}
