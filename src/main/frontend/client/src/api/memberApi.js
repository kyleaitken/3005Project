import { API_ENDPOINTS } from './config';
import { get, del } from './utils';

export const getMemberSchedule = (memberId) => {
    const url = API_ENDPOINTS.memberSchedule(memberId);
    return get(url);
};

export const getMemberUpcomingSessions = (memberId) => {
    const url = API_ENDPOINTS.getMemberUpcomingSessions(memberId);
    return get(url);
}

export const getMemberPastSessions = (memberId) => {
    const url = API_ENDPOINTS.getMemberPastSessions(memberId);
    return get(url);
}

export const cancelMemberSession = (sessionId) => {
    const url = API_ENDPOINTS.cancelMemberSession(sessionId);
    return del(url);
}