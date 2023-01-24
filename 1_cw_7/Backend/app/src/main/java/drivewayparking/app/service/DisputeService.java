package drivewayparking.app.service;

import drivewayparking.app.dto.DisputeRequest;
import drivewayparking.app.dto.DisputeResponse;
import drivewayparking.app.entity.Admin;
import drivewayparking.app.entity.Booking;
import drivewayparking.app.entity.Dispute;
import drivewayparking.app.entity.Property;
import drivewayparking.app.enums.Status;
import drivewayparking.app.repository.DisputeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisputeService {

    @Autowired
    private DisputeRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private BookingService bookingService;

    public DisputeResponse getDisputeById(Long id) {
        Dispute existingDispute = repository.findById(id).orElse(null);
        if (existingDispute != null) return disputeToResponse(existingDispute);
        else return null;
    }

    public DisputeResponse getDisputeByBookingId(Long id) {
        Dispute existingDispute = repository.findByBooking(id);
        if (existingDispute != null) return disputeToResponse(existingDispute);
        else return null;
    }

    public List<DisputeResponse> getDisputes() {
        List<Dispute> existingDisputes = repository.findAll();
        List<DisputeResponse> disputeResponseList = new ArrayList<>();

        for (Dispute dispute : existingDisputes) { disputeResponseList.add(disputeToResponse(dispute)); }

        return disputeResponseList;
    }

    public List<DisputeResponse> getDisputesByAdminId(Long id) {
        Admin existingAdmin = adminService.getAdminById(id);
        if (existingAdmin != null) {
            List<Dispute> existingDisputes = repository.findByAdmin(id);
            List<DisputeResponse> disputeResponseList = new ArrayList<>();

            for (Dispute dispute : existingDisputes) { disputeResponseList.add(disputeToResponse(dispute)); }

            return disputeResponseList;
        }

        else return null;
    }


    public List<DisputeResponse> getUnresolvedDisputesByAdminId(Long id) {
        Admin existingAdmin = adminService.getAdminById(id);
        if (existingAdmin != null) {
            List<Dispute> existingDisputes = repository.findUnresolvedByAdmin(id);
            List<DisputeResponse> disputeResponseList = new ArrayList<>();

            for (Dispute dispute : existingDisputes) { disputeResponseList.add(disputeToResponse(dispute)); }

            return disputeResponseList;
        }

        else return null;
    }

    public Integer saveDispute(DisputeRequest request) {

        Dispute existingDispute = repository.findByBooking(request.getBookingId());
        Booking existingBooking = bookingService.getBookingById(request.getBookingId());

        if (existingDispute == null && existingBooking != null) {

            Dispute dispute = new Dispute();
            dispute.setBooking(existingBooking); // setting one-to-one relationship between dispute and booking
            dispute.setMessage(request.getMessage());
            dispute.setAdmin(assignAdmin()); // setting many-to-one relationship between dispute and admin
            repository.save(dispute);

            return Status.OK.getValue();
        }

        return Status.Error.getValue();
    }

    public Integer resolveDispute(Long id) {

        Dispute existingDispute = repository.findById(id).orElse(null);

        if (existingDispute != null) {
            existingDispute.setResolved(true);
            repository.save(existingDispute);

            return Status.OK.getValue();
        }

        return Status.Not_Found.getValue();
    }

    public Integer deleteDispute(Long id) {

        Dispute existingDispute = repository.findById(id).orElse(null);
        if (existingDispute != null) {
            repository.deleteById(id);
            return Status.OK.getValue();
        }
        return Status.Not_Found.getValue();
    }

    // Helper method
    private DisputeResponse disputeToResponse(Dispute dispute) {
        DisputeResponse disputeResponse = new DisputeResponse();
        disputeResponse.setId(dispute.getId());
        disputeResponse.setBookingId(dispute.getBooking().getId());
        disputeResponse.setAdminId(dispute.getAdmin().getId());
        disputeResponse.setMessage(dispute.getMessage());
        disputeResponse.setResolved(dispute.getResolved());
        disputeResponse.setCreated(dispute.getCreated());

        return disputeResponse;
    }

    // Helper method
    private Admin assignAdmin() { return adminService.getAdminByLowestNumberDisputesAssigned(); }
}
