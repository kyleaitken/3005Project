package com.example.project.Services;

import com.example.project.Models.Member;
import com.example.project.Models.MemberHealthInfo;
import com.example.project.Repos.MemberHealthInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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

    public void updateOrSaveMemberHealthInfo(Long memberId, MemberHealthInfo updatedMemberHealthInfo) {
        Optional<MemberHealthInfo> existingMemberHealthInfoOpt = memberHealthInfoRepository.findById(memberId);
    
        if (existingMemberHealthInfoOpt.isPresent()) {
            updatedMemberHealthInfo.setMemberId(memberId); 
            memberHealthInfoRepository.update(updatedMemberHealthInfo);
        } else {
            updatedMemberHealthInfo.setMemberId(memberId); 
            memberHealthInfoRepository.save(updatedMemberHealthInfo);
        }
    }
    

}
