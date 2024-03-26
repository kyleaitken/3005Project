import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { getMembers, getMember } from '../api/trainerApi';



const TrainerMembersView = () => {
    const [members, setMembers] = useState([]);
    const [selectedMember, setSelectedMember] = useState(null);

    const fetchMembers = async () => {
        const res = await getMembers();
        setMembers(res);
    }

    const fetchMemberDetails = async (memberId) => {
        const res = await getMember(memberId);
        console.log(res);
        setSelectedMember(res);
    }

    useEffect(() => {
        fetchMembers();
    }, []);

    const handleMemberClick = async (memberId) => {
        fetchMemberDetails(memberId);
    };

    return (
        <Layout>
            <MemberList>
                {members.map(member => (
                    <MemberItem key={member.memberId}>
                        <MemberLink onClick={() => handleMemberClick(member.memberId)}>
                            {member.memberFirstName} {member.memberLastName}
                        </MemberLink>
                    </MemberItem>
                ))}
            </MemberList>
            <MemberDetails>
                {selectedMember ? (
                    <div>
                        <p><strong>Name:</strong> {selectedMember.firstName} {selectedMember.lastName}</p>
                        <p><strong>DoB:</strong> {selectedMember.memberBirthDate}</p>
                        <p><strong>Email:</strong> {selectedMember.memberEmail}</p>
                        <p><strong>Phone:</strong> {selectedMember.memberPhone}</p>
                        <p><strong>Height:</strong> {selectedMember.memberHeight}</p>
                        <p><strong>Weight:</strong> {selectedMember.memberWeight}</p> {/* Note: Corrected to memberWeight */}
                        <p><strong>BMI:</strong> {selectedMember.memberBMI}</p>
                        <p><strong>Resting HR:</strong> {selectedMember.memberRestingHR}</p>
                        <p><strong>Systolic BP:</strong> {selectedMember.memberSysBP}</p>
                        <p><strong>Diastolic BP:</strong> {selectedMember.memberDiaBP}</p>
                        <p><strong>Waist Girth:</strong> {selectedMember.memberWaist}</p>
                    </div>
                ) : <></>}
            </MemberDetails>
        </Layout>
    );
};

export default TrainerMembersView;


// Styled components
const Layout = styled.div`
  display: flex;
  height: 100%;
  
`;

const MemberList = styled.div`
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  width: 500px;

`;

const MemberItem = styled.div`
  margin: 20px;
`;

const MemberLink = styled.a`
  font-size: 18px;
  text-decoration: none;
  color: blue;
  cursor: pointer;
`;

const MemberDetails = styled.div`
  overflow-y: auto;
  font-size: 18px;
  width: 500px;
`;

