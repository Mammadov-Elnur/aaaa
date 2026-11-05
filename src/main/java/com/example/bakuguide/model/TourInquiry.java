package com.example.bakuguide.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tour_inquiries")
public class TourInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter your name")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Please enter your email")
    @Email(message = "Please enter a valid email")
    private String email;

    @Size(max = 30)
    private String phone;

    @NotNull(message = "Please choose a preferred date")
    @FutureOrPresent(message = "Date cannot be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate preferredDate;

    @Min(value = 1, message = "At least 1 person")
    @Max(value = 50, message = "Maximum 50 people")
    private int groupSize = 1;

    @NotBlank(message = "Please select a tour type")
    private String tourType;

    @Size(max = 1000, message = "Message is too long (max 1000 characters)")
    @Column(length = 1000)
    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public LocalDate getPreferredDate() { return preferredDate; }
    public void setPreferredDate(LocalDate preferredDate) { this.preferredDate = preferredDate; }
    public int getGroupSize() { return groupSize; }
    public void setGroupSize(int groupSize) { this.groupSize = groupSize; }
    public String getTourType() { return tourType; }
    public void setTourType(String tourType) { this.tourType = tourType; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
