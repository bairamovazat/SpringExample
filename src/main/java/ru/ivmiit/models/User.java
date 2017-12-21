package ru.ivmiit.models;


import lombok.*;
import ru.ivmiit.security.role.Role;
import ru.ivmiit.security.states.State;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chat_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Chat> chats;
    private UUID uuid;
    private String status;
    private String phone;

    @Column(unique = true)
    private String login;

    private String hashPassword;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private State state;

    private String hashTempPassword;

    private String email;

}
