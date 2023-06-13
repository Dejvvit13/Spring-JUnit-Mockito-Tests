package io.github.dejvvit13.springjunitmockitotests.gymmember.controller;

import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import io.github.dejvvit13.springjunitmockitotests.gymmember.service.GymMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gymMembers")
@RequiredArgsConstructor
public class GymMemberController {

    private final GymMemberService gymMemberService;

    @PostMapping
    public ResponseEntity<GymMember> createGymMember(@RequestBody GymMember gymMember) {
        return new ResponseEntity<>(gymMemberService.saveGymMember(gymMember), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<GymMember>> getAllGymMembers() {
        return ResponseEntity.ok(gymMemberService.getAllGymMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymMember> getGymMemberById(@PathVariable Long id) {
        return gymMemberService.getGymMemberById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GymMember> updateEmployee(@PathVariable Long id, @RequestBody GymMember gymMember) {
        return gymMemberService.getGymMemberById(id)
                .map(gm -> {
                    gymMember.setId(id);
                    return ResponseEntity.ok(gymMemberService.updateGymMember(gymMember));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGymMember(@PathVariable Long id) {
        gymMemberService.deleteEmployee(id);
        return ResponseEntity.ok("Deleted member with id: " + id);
    }

}
