import { useState, useEffect, useCallback } from "react";
import { useAuth } from "../contexts/AuthContext";
import styled from "styled-components";
import { getMemberProfile, updateMemberProfile } from "../api/memberApi";

const MemberProfile = () => {
    const [profileInfo, setProfileInfo] = useState({
        firstName: '',
        lastName: '',
        email: '',
        birthDate: '',
        phone: '',
        address: '',
        emergencyPhone: '',
    });

    const {userId} = useAuth();

    const getProfileInfo = useCallback(async() => {
        const res = await getMemberProfile(userId)
        setProfileInfo(res);
    }, [userId])

    useEffect(() => {
        getProfileInfo();
    }, [getProfileInfo])


    const handleChange = (e) => {
        const { name, value } = e.target;
        setProfileInfo(prev => ({
            ...prev,
            [name]: value
        }));
    };
    

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await updateMemberProfile(userId, profileInfo);
            if (response.message === "Success") {
                window.alert("Information updated.")
            } else {
                window.alert("Unable to update member profile info.")
            }
        } catch {
            window.alert("Error: unable to update member profile info.")
        }
        getProfileInfo();
    };

    const formatLabel = (label) => {
        // Split words when there is a lowercase letter followed directly by an uppercase letter
        const spacedLabel = label.replace(/([a-z])([A-Z])/g, '$1 $2');
        
        // Capitalize the first letter of the resulting string
        return spacedLabel.charAt(0).toUpperCase() + spacedLabel.slice(1);
      };

    return (
        <ProfileView>
        <h2>Profile Info</h2>
        <Form onSubmit={handleSubmit}>
            {Object.entries(profileInfo).map(([key, value]) => (
                key !== "memberId" && key !== "password" && (
                    <InputView key={key}>
                        <Label >
                            {formatLabel(key)}:
                        </Label>
                        <Input
                            type="string"
                            name={key}
                            value={value}
                            onChange={handleChange}
                            readOnly={key === 'firstName' || key === 'lastName' || key === 'birthDate'}
                        />
                    </InputView>
                )
            ))}
        <SaveButton type="submit">Save</SaveButton>
        </Form>
        </ProfileView>
    );
}

export default MemberProfile;

const ProfileView = styled.div`
    display: flex;
    flex-direction: column;
    margin-left: 40px;
`

const Form = styled.form`
    display: flex;
    flex-direction: column;
`

const Label = styled.div`
    font-size: 18px;
    margin: 10px 0px;
    width: 200px;
`

const Input = styled.input`
    font-size: 16px;
    margin-left: 10px;
    width: 400px;
`
const InputView = styled.div`
    display: flex;
    flex-direction: row;
    margin: 10px 0px;
`

const SaveButton = styled.button`
    width: 120px;
`


