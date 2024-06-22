"use client"

import { useAuth } from "@/hooks/auth-provider";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { Checkbox } from "@/components/ui/checkbox"
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

type UserRole = "ROLE_BUYER" | "ROLE_SELLER";
type UserSignUpDetails = {
  username: string,
  password: string,
  email: string,
  firstName: string,
  lastName: string,
  roles: UserRole[],
  shippingAddress: string,
  billingAddress: string,
  defaultPaymentMethod: string,
  businessName: string,
  businessDescription:string,
};

const enumLike = <T extends string>(x: T): { [k in T]: null } => {
    return { [x]: null } as any;
  };

const formSchema = z.object({
  username: z.string().min(2, {
    message: "Username must be at least 2 characters.",
  }),
  password: z.string().min(6, {
    message: "Password must be at least 6 characters.",
  }),
  email: z.string().email({
    message: "Invalid email address.",
  }),
  firstName: z.string().nonempty({
    message: "First name is required.",
  }),
  lastName: z.string().nonempty({
    message: "Last name is required.",
  }),
  // roles: z.array(z.nativeEnum(enumLike("ROLE_BUYER") as any, enumLike("ROLE_SELLER") as any)),
  shippingAddress: z.string().min(5, {
    message: "Shipping address is required.",
  }),
  billingAddress: z.string().min(5, {
    message: "Billing address is required.",
  }),
  defaultPaymentMethod: z.string().nonempty({
    message: "Default payment method is required.",
  }),
  businessName: z.string().min(2, {
    message: "Business name is required.",
  }),
  businessDescription: z.string().min(10, {
    message: "Business description must be at least 10 characters.",
  }),
});
export default function SignUpForm() {

    const auth = useAuth();

    const form = useForm<z.infer<typeof formSchema>>({
      resolver: zodResolver(formSchema),
      defaultValues: {
        username: "",
        password: "",
        email: "",
        firstName: "",
        lastName: "",
        // roles: [] as UserRole[],
        shippingAddress: "",
        billingAddress: "",
        defaultPaymentMethod: "",
        businessName: "",
        businessDescription: "",
      },
    });
  
    function onSubmit(values: z.infer<typeof formSchema>) {
      console.log("Inside onSubmit");

      const formattedValues: UserSignUpDetails = {
        ...values,
        // roles: values.roles as UserRole[],
        roles: ["ROLE_BUYER"],
      };
      console.log(formattedValues);
      
      try{
          auth?.signUp(formattedValues);
          alert("Successful!")
      }
      catch(error){
          alert("Error in signing up")
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
                  <FormDescription>
                    This is your public display name.
                  </FormDescription>
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
            {/* {[
                {
                    id: "recents",
                    label: "ROLE_BUYER",
                },
                {
                    id: "home",
                    label: "ROLE_SELLER",
                }].map((item) => (
                    <FormField
                    key={item.id}
                    control={form.control}
                    name="roles"
                    render={({ field }) => {
                        return (
                        <FormItem
                            key={item.id}
                            className="flex flex-row items-start space-x-3 space-y-0"
                        >
                            <FormControl>
                            <Checkbox
                              checked={field.value.includes(item.id)}
                              onCheckedChange={(checked) => {
                                const roles = field.value || [];
                                const updatedRoles = checked
                                  ? [...roles, item.id]
                                  : roles.filter((role) => role !== item.id);
                                field.onChange(updatedRoles);
                              }}
                            />
                            </FormControl>
                            <FormLabel className="font-normal">
                            {item.label}
                            </FormLabel>
                        </FormItem>
                        )
                    }}
                    />
              ))} */}
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
            <FormField
              control={form.control}
              name="defaultPaymentMethod"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Default Payment Method</FormLabel>
                  <FormControl>
                    <Input placeholder="Default Payment Method" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
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
                    <textarea placeholder="Business Description" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <Button type="submit">Submit</Button>
          </form>
        </Form>
      </section>
    );
  }