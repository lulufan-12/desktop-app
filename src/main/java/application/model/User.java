package application.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

@Getter
@Setter
@ToString
@Entity(name = "User")
@RequiredArgsConstructor
@Table(name = "en_user")
@EqualsAndHashCode(callSuper = true)
public class User extends DefaultModel {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "se_user_id", allocationSize = 1, sequenceName = "se_user_id")
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
		name = "re_user_role",
		joinColumns = { @JoinColumn(name = "user_id") },
		inverseJoinColumns = { @JoinColumn(name = "role_id") }
	)
    private Set<Role> roles = new HashSet<>();

}
