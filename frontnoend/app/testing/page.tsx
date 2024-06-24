"use client"

import {useEffect, useState} from 'react';
import { useAuth } from "@/hooks/auth-provider"


const TestPage = () => {
  const auth = useAuth();
  const user = auth?.user || null;

  const [accessToken, setAccessToken] = useState<undefined | string>(undefined);
  const [refreshToken, setRefreshToken] = useState<undefined | string>(undefined);

  useEffect(() => {
    
    const getAccessToken = async () => {
      const accessToken = await auth?.obtainAccessToken();

      setAccessToken(accessToken);
    }

    getAccessToken();
    
  }, [user])
  


  return (
    <div className="w-full flex_col_center gap-4">
      <p>
        Email: {user?.email}
      </p>

      <p>
        Access Token: {accessToken}
      </p>

    </div>
  )
}

export default TestPage