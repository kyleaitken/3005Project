import { Navigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

const MemberHomeScreen = () => {
    const { userId } = useAuth();

    if (!userId) {
        console.log("re-routing to login screen")
        return <Navigate to="/" replace />;
    }

    return (
        <h1> It's the Member Home screen </h1>
    )
}

export default MemberHomeScreen;