import { useState, useEffect, useCallback } from "react";
import { useAuth } from "../contexts/AuthContext";
import styled from "styled-components";
import { getMemberHealthInfo, updateMemberHealthInfo } from "../api/memberApi";

const MemberHealthInfo = () => {
    const [healthInfo, setHealthInfo] = useState({
        height: '',
        weight: '',
        bmi: '',
        restingHeartRate: '',
        systolicBp: '',
        diastolicBp: '',
        waistGirth: '',
    });

    const {userId} = useAuth();

    const getHealthInfo = useCallback(async() => {
        const res = await getMemberHealthInfo(userId)
        console.log(res)
        setHealthInfo(res);
    }, [userId])

    useEffect(() => {
        getHealthInfo();
    }, [getHealthInfo])


    const handleChange = (e) => {
        const { name, value } = e.target;
        setHealthInfo(prev => ({
            ...prev,
            [name]: value === "" ? "" : Number(value)
        }));
    };
    

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            console.log('info to send', healthInfo);
            const response = await updateMemberHealthInfo(userId, healthInfo);
            window.alert("Information updated.")
        } catch {
            window.alert("Unable to update member health info.")
        }
        getHealthInfo();
    };

    const formatLabel = (label) => {
        // Split words when there is a lowercase letter followed directly by an uppercase letter
        const spacedLabel = label.replace(/([a-z])([A-Z])/g, '$1 $2');
        
        // Capitalize the first letter of the resulting string
        return spacedLabel.charAt(0).toUpperCase() + spacedLabel.slice(1);
      };

    return (
        <HealthInfoView>
        <h2>Health Info</h2>
        <Form onSubmit={handleSubmit}>
            {Object.entries(healthInfo).map(([key, value]) => (
                key !== "memberId" && (
                    <InputView key={key}>
                        <Label >
                            {formatLabel(key)}:
                        </Label>
                        <Input
                            type="number"
                            name={key}
                            value={value}
                            onChange={handleChange}
                        />
                    </InputView>
                )
            ))}
        <SaveButton type="submit">Save</SaveButton>
        </Form>
        </HealthInfoView>
    );
}

export default MemberHealthInfo;

const HealthInfoView = styled.div`
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
    width: 100px;
`
const InputView = styled.div`
    display: flex;
    flex-direction: row;
    margin: 10px 0px;
`

const SaveButton = styled.button`
    width: 120px;
`


