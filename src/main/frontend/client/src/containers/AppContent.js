import styled from "styled-components";
import { useAuth } from "../contexts/AuthContext";
import NavigationSideBar from "../components/NavigationSideBar";
import MemberHomeScreen from "../screens/MemberHomeScreen";
import LoginScreen from "../screens/LoginScreen";
import MemberSchedule from "./MemberSchedule";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';



const AppContent = () => {
    const { isLoggedIn } = useAuth();

    return (
        <AppContentView>
            {isLoggedIn && <NavigationSideBar />}
            <Routes>
                <Route path="/" element={<LoginScreen />} />
                <Route path="/member" element={<MemberHomeScreen />} />
                <Route path="/schedule" element={<MemberSchedule />} />
            </Routes>
        </AppContentView>
    )

}


export default AppContent;

const AppContentView = styled.div`
    display: flex;
`