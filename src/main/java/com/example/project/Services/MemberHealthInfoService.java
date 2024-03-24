package com.example.project.Services;

import com.example.project.Models.MemberHealthInfo;
import com.example.project.Repos.MemberHealthInfoRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.DrbgParameters.Reseed;
import java.util.Optional;

@Service
public class MemberHealthInfoService {

    private final MemberHealthInfoRepository memberHealthInfoRepository;

    @Autowired
    public MemberHealthInfoService(MemberHealthInfoRepository memberHealthInfoRepository) {
        this.memberHealthInfoRepository = memberHealthInfoRepository;
    }

    public Optional<MemberHealthInfo> findById(Long memberId) {
        return memberHealthInfoRepository.findById(memberId);
    }

    public ResponseEntity<Optional<MemberHealthInfo>> updateOrSaveMemberHealthInfo(Long memberId, MemberHealthInfo updatedMemberHealthInfo) {
        Optional<MemberHealthInfo> existingMemberHealthInfoOpt = memberHealthInfoRepository.findById(memberId);
        
        if (existingMemberHealthInfoOpt.isPresent()) {
            System.out.println("member exists");
            updatedMemberHealthInfo.setMemberId(memberId); 
            boolean updateSuccessful = memberHealthInfoRepository.update(updatedMemberHealthInfo);
            System.out.println("update success " + updateSuccessful);
            if (updateSuccessful) return ResponseEntity.ok(Optional.of(updatedMemberHealthInfo));
        } else {
            updatedMemberHealthInfo.setMemberId(memberId); 
            memberHealthInfoRepository.save(updatedMemberHealthInfo);
            return ResponseEntity.ok(Optional.of(updatedMemberHealthInfo));
        }

        return ResponseEntity.ok(Optional.empty());

    }
    

}
