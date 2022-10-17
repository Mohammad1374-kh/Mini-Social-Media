package com.mohammad.msm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friendship")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follow_request_sender")
    private User followRequestSender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follow_request_receiver")
    private User followRequestReceiver;

    @Column(name = "accepted")
    private Boolean accepted;
}
