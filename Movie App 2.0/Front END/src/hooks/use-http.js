import {useCallback, useState} from "react";

const useHttp = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchData = async (url, requestSpecification) => {
        const response = await fetch(url, requestSpecification);
        let responseJSON;
        if (response.ok || response.status === 201) {
            responseJSON = await response.json();
            return responseJSON;
        }
        try {
            responseJSON = await response.json();
        } catch (error) {
            responseJSON = {code: 500, message: 'Internal server error'}
        }
        throw new Error(responseJSON.message);
    }

    const sendRequest = useCallback(async (requestConfiguration, setData) => {
        setIsLoading(true);
        setError(null);
        try {
            const url = requestConfiguration.url;
            const requestSpecification = {
                method: requestConfiguration.method ? requestConfiguration.method : 'GET',
                headers: requestConfiguration.header ? requestConfiguration.header : {},
                body: requestConfiguration.body ? JSON.stringify(requestConfiguration.body) : null
            }
            const data = await fetchData(url, requestSpecification);
            setData(data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
        setIsLoading(false);
    }, []);

    return {isLoading, error, sendRequest};
}

export default useHttp;
