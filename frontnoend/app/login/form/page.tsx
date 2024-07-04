"use client";

import { useAuth } from "@/hooks/auth-provider";
import { useState } from "react";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { Button } from "@/components/ui/button";
import TikTokLoader from "@/components/shared/TiktokLoader";
import {
  Form,
  FormField,
  FormControl,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";


const formSchema = z.object({
  username: z.string().min(2, {
    message: "Username must be at least 2 characters.",
  }),
  password: z.string().min(6, {
    message: "Password must be at least 6 characters.",
  }),
});

export default function SignInForm() {

    const auth = useAuth();

    const [isSigningIn, setIsSigningIn] = useState<boolean>(false);

    const form = useForm<z.infer<typeof formSchema>>({
      resolver: zodResolver(formSchema),
      defaultValues: {
        username: "",
        password: "",
      },
    });
  
    async function onSubmit(values: z.infer<typeof formSchema>) {
      setIsSigningIn(true);
      const formattedValues: UserSignInDetails = {
        ...values,
      };
      console.log(formattedValues);
      
      try {
          await auth?.signIn({
            username: formattedValues.username,
            password: formattedValues.password
          });
      } catch(error) {
          alert("Error in signing in")
      }
      finally{
        setIsSigningIn(false);
      }
    }
  
    return (
      <section className="w-full flex_center">
        <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
            <FormField
              control={form.control}
              name="username"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Username</FormLabel>
                  <FormControl>
                    <Input placeholder="Username" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="password"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Password</FormLabel>
                  <FormControl>
                    <Input type="password" placeholder="Password" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <Button type="submit">
              {isSigningIn ? 'Logging In' : 'Log In'}
            </Button>
            {isSigningIn &&
              <div className="flex_center w-full">
                <TikTokLoader />
              </div>
            }
          </form>
        </Form>
      </section>
    );
  }
