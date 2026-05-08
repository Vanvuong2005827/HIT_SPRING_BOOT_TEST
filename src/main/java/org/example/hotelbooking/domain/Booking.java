package org.example.hotelbooking.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_cccd", nullable = false, length = 50)
    private String customerCccd;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "check_in_datetime", nullable = false)
    private Instant checkInDateTime;

    @Column(name = "check_out_datetime", nullable = false)
    private Instant checkOutDateTime;

    @Column(name = "number_of_guests", nullable = false)
    private Integer numberOfGuests;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private BookingStatus status;

    @Column(name = "total_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalPrice;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        Instant now = Instant.now();
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        if (status == null) {
            status = BookingStatus.PENDING;
        }
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCccd() {
        return customerCccd;
    }

    public void setCustomerCccd(String customerCccd) {
        this.customerCccd = customerCccd;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Instant getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(Instant checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public Instant getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(Instant checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
