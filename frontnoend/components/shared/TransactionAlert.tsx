'use client'

import { useEffect, useState } from 'react';
import { AlertDialog, AlertDialogContent, AlertDialogHeader, AlertDialogTitle, AlertDialogDescription } from '../ui/alert-dialog';
import { FaCheckCircle } from 'react-icons/fa';

interface AlertProps {
  open: boolean;
  onClose: () => void;
  alertContent: string;
}

const TransactionAlert: React.FC<AlertProps> = ({ open, onClose, alertContent }) => {
  const [timer, setTimer] = useState<NodeJS.Timeout | null>(null);

  useEffect(() => {
    if (open) {
      // Set a timer to close the alert after 2 seconds
      const timerId = setTimeout(() => {
        onClose();
      }, 2000);

      // Save the timer ID to clear it on component unmount or when open state changes
      setTimer(timerId);
    }

    // Clear the timer when the component unmounts or open state changes
    return () => {
      if (timer) {
        clearTimeout(timer);
      }
    };
  }, [open, onClose]);

  return (
    <AlertDialog open={open} onOpenChange={onClose}>
      <AlertDialogContent className="w-[1200px]">
        <AlertDialogHeader>
          <AlertDialogTitle>Transaction Status</AlertDialogTitle>
          <AlertDialogDescription>
            {alertContent.includes("Success") ? (
              <div className="flex items-center">
                <FaCheckCircle className="text-green-500 mr-2" size={24} />
                <p>{alertContent}</p>
              </div>
            ) : (
              <div className="flex items-center">
                <p>{alertContent}</p>
              </div>
            )}
          </AlertDialogDescription>
        </AlertDialogHeader>
      </AlertDialogContent>
    </AlertDialog>
  );
};

export default TransactionAlert;
