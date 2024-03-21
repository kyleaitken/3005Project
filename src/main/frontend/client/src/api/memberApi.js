import { API_ENDPOINTS } from './config';
import { get } from './utils';

export const getMemberSchedule = (memberId) => {
    const url = API_ENDPOINTS.memberSchedule(memberId);
    return get(url);
};