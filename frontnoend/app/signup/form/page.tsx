"use client"

import { useAuth } from "@/hooks/auth-provider";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { useState } from "react";
import TikTokLoader from "@/components/shared/TiktokLoader";

type UserRole = "ROLE_BUYER" | "ROLE_SELLER" | "ROLE_BOTH";

const formSchema = z.object({
  username: z.string().min(2, { message: "Username must be at least 2 characters." }),
  password: z.string().min(6, { message: "Password must be at least 6 characters." }),
  email: z.string().email({ message: "Invalid email address." }),
  firstName: z.string().nonempty({ message: "First name is required." }),
  lastName: z.string().nonempty({ message: "Last name is required." }),
  role: z.enum(["ROLE_BUYER", "ROLE_SELLER", "ROLE_BOTH"]),
  shippingAddress: z.string().min(5, { message: "Shipping address is required." }).optional(),
  billingAddress: z.string().min(5, { message: "Billing address is required." }).optional(),
  walletAddress: z.string().min(5, { message: "Wallet address is required." }),
  businessName: z.string().min(2, { message: "Business name is required." }).optional(),
  businessDescription: z.string().min(10, { message: "Business description must be at least 10 characters." }).optional(),
});

export default function SignUpForm() {
  const auth = useAuth();
  const [userRole, setUserRole] = useState<UserRole>("ROLE_BUYER");

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      username: "",
      password: "",
      email: "",
      firstName: "",
      lastName: "",
      role: "ROLE_BUYER",
      shippingAddress: "",
      billingAddress: "",
      walletAddress: "",
      businessName: "",
      businessDescription: "",
    },
  });

  function onSubmit(values: z.infer<typeof formSchema>) {
    const formattedValues = {
      ...values,
      roles: userRole === "ROLE_BOTH" ? ["ROLE_BUYER", "ROLE_SELLER"] : [userRole],
    };
    console.log(formattedValues);
    
    try {
      alert("Signing Up not implemented!")
      // auth?.signUp(formattedValues);
    } catch(error) {
      alert("Error in signing up")
    }
  }

  return (
    <Card className="w-[800px] mx-auto">
      <CardContent className="py-8">
        <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
            <div className="grid grid-cols-2 gap-4">
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
            </div>
            <div className="grid grid-cols-2 gap-4">
              <FormField
                control={form.control}
                name="email"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Email</FormLabel>
                    <FormControl>
                      <Input type="email" placeholder="Email" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="walletAddress"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Wallet Address</FormLabel>
                    <FormControl>
                      <Input placeholder="Wallet Address" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <div className="grid grid-cols-2 gap-4">
              <FormField
                control={form.control}
                name="firstName"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>First Name</FormLabel>
                    <FormControl>
                      <Input placeholder="First Name" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="lastName"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Last Name</FormLabel>
                    <FormControl>
                      <Input placeholder="Last Name" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <FormField
              control={form.control}
              name="role"
              render={({ field }) => (
                <FormItem className="space-y-3">
                  <FormLabel>Account Type</FormLabel>
                  <FormControl>
                    <RadioGroup
                      onValueChange={(value: UserRole) => {
                        setUserRole(value);
                        field.onChange(value);
                      }}
                      defaultValue={field.value}
                      className="flex flex-col space-y-1"
                    >
                      <FormItem className="flex items-center space-x-3 space-y-0">
                        <FormControl>
                          <RadioGroupItem value="ROLE_BUYER" />
                        </FormControl>
                        <FormLabel className="font-normal">
                          Buyer
                        </FormLabel>
                      </FormItem>
                      <FormItem className="flex items-center space-x-3 space-y-0">
                        <FormControl>
                          <RadioGroupItem value="ROLE_SELLER" />
                        </FormControl>
                        <FormLabel className="font-normal">
                          Seller
                        </FormLabel>
                      </FormItem>
                      <FormItem className="flex items-center space-x-3 space-y-0">
                        <FormControl>
                          <RadioGroupItem value="ROLE_BOTH" />
                        </FormControl>
                        <FormLabel className="font-normal">
                          Both
                        </FormLabel>
                      </FormItem>
                    </RadioGroup>
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            
            {(userRole === "ROLE_BUYER" || userRole === "ROLE_BOTH") && (
              <div className="grid grid-cols-2 gap-4">
                <FormField
                  control={form.control}
                  name="shippingAddress"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Shipping Address</FormLabel>
                      <FormControl>
                        <Input placeholder="Shipping Address" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="billingAddress"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Billing Address</FormLabel>
                      <FormControl>
                        <Input placeholder="Billing Address" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
              </div>
            )}
            
            {(userRole === "ROLE_SELLER" || userRole === "ROLE_BOTH") && (
              <div className="grid grid-cols-2 gap-4">
                <FormField
                  control={form.control}
                  name="businessName"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Business Name</FormLabel>
                      <FormControl>
                        <Input placeholder="Business Name" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="businessDescription"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Business Description</FormLabel>
                      <FormControl>
                        <Input placeholder="Business Description" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
              </div>
            )}
            
            <Button type="submit" className="w-full ">
              Signing Up...

            </Button>
            <div className="flex_center w-full">

            <TikTokLoader />
            </div>

          </form>
        </Form>
      </CardContent>
    </Card>
  );
}