0x006db000:	pushl %esi
0x006db001:	pushl %eax
0x006db002:	pushl %ebx
0x006db003:	call 0x006db009
0x006db009:	popl %eax
0x006db00a:	movl %ebx, %eax
0x006db00c:	incl %eax
0x006db00d:	subl %eax, $0x137000<UINT32>
0x006db012:	subl %eax, $0x100c6b00<UINT32>
0x006db017:	addl %eax, $0x100c6af7<UINT32>
0x006db01c:	cmpb (%ebx), $0xffffffcc<UINT8>
0x006db01f:	jne 25
0x006db021:	movb (%ebx), $0x0<UINT8>
0x006db024:	movl %ebx, $0x1000<UINT32>
0x006db029:	pushl $0x3338722d<UINT32>
0x006db02e:	pushl $0x2979e390<UINT32>
0x006db033:	pushl %ebx
0x006db034:	pushl %eax
0x006db035:	call 0x006db044
0x006db044:	pushl %ebp
0x006db045:	movl %ebp, %esp
0x006db047:	pushl %eax
0x006db048:	pushl %ebx
0x006db049:	pushl %ecx
0x006db04a:	pushl %esi
0x006db04b:	movl %esi, 0x8(%ebp)
0x006db04e:	movl %ecx, 0xc(%ebp)
0x006db051:	shrl %ecx, $0x2<UINT8>
0x006db054:	movl %eax, 0x10(%ebp)
0x006db057:	movl %ebx, 0x14(%ebp)
0x006db05a:	testl %ecx, %ecx
0x006db05c:	je 0x006db068
0x006db05e:	xorl (%esi), %eax
0x006db060:	addl (%esi), %ebx
0x006db062:	addl %esi, $0x4<UINT8>
0x006db065:	decl %ecx
0x006db066:	jmp 0x006db05a
0x006db068:	popl %esi
0x006db069:	popl %ecx
0x006db06a:	popl %ebx
0x006db06b:	popl %eax
0x006db06c:	leave
0x006db06d:	ret $0x10<UINT16>

0x006db03a:	addl %eax, $0x0<UINT8>
0x006db03d:	movl 0x8(%esp), %eax
0x006db041:	popl %ebx
0x006db042:	popl %eax
0x006db043:	ret

0x005a4000:	pusha
0x005a4001:	call 0x005a4006
0x005a4006:	popl %eax
0x005a4007:	addl %eax, $0x58<UINT32>
0x005a400c:	cmpb (%eax), $0xffffffe9<UINT8>
0x005a400f:	jne 0x005a4024
0x005a4024:	call 0x005a4029
0x005a4029:	popl %eax
0x005a402a:	subl %eax, $0x29<UINT32>
0x005a402f:	subl %eax, $0x1a4000<UINT32>
0x005a4034:	xorl %edi, %edi
0x005a4036:	movw %bx, $0x5a19<UINT16>
0x005a403a:	addw %bx, $0x34<UINT8>
0x005a403e:	cmpw (%eax), %bx
0x005a4041:	jne 18
0x005a4043:	movzwl %edx, 0x3c(%eax)
0x005a4047:	addl %edx, %eax
0x005a4049:	movl %ebx, $0x44e9<UINT32>
0x005a404e:	addl %ebx, $0x67<UINT8>
0x005a4051:	cmpl (%edx), %ebx
0x005a4053:	je 0x005a405c
0x005a405c:	movl %edi, %eax
0x005a405e:	movl %eax, $0xa6014<UINT32>
0x005a4063:	addl %eax, %edi
0x005a4065:	movl %ecx, $0x1a4212<UINT32>
0x005a406a:	addl %ecx, %edi
0x005a406c:	pushl %eax
0x005a406d:	pushl %ecx
0x005a406e:	call 0x005a40bf
0x005a40bf:	pusha
0x005a40c0:	movl %esi, 0x24(%esp)
0x005a40c4:	movl %edi, 0x28(%esp)
0x005a40c8:	cld
0x005a40c9:	movb %dl, $0xffffff80<UINT8>
0x005a40cb:	movb %al, (%esi)
0x005a40cd:	incl %esi
0x005a40ce:	movb (%edi), %al
0x005a40d0:	incl %edi
0x005a40d1:	movl %ebx, $0x2<UINT32>
0x005a40d6:	addb %dl, %dl
0x005a40d8:	jne 0x005a40df
0x005a40da:	movb %dl, (%esi)
0x005a40dc:	incl %esi
0x005a40dd:	adcb %dl, %dl
0x005a40df:	jae 0x005a40cb
0x005a40e1:	addb %dl, %dl
0x005a40e3:	jne 0x005a40ea
0x005a40ea:	jae 0x005a413b
0x005a40ec:	xorl %eax, %eax
0x005a40ee:	addb %dl, %dl
0x005a40f0:	jne 0x005a40f7
0x005a40f7:	jae 0x005a41dc
0x005a40fd:	addb %dl, %dl
0x005a40ff:	jne 0x005a4106
0x005a4106:	adcl %eax, %eax
0x005a4108:	addb %dl, %dl
0x005a410a:	jne 0x005a4111
0x005a4111:	adcl %eax, %eax
0x005a4113:	addb %dl, %dl
0x005a4115:	jne 0x005a411c
0x005a411c:	adcl %eax, %eax
0x005a411e:	addb %dl, %dl
0x005a4120:	jne 0x005a4127
0x005a4122:	movb %dl, (%esi)
0x005a4124:	incl %esi
0x005a4125:	adcb %dl, %dl
0x005a4127:	adcl %eax, %eax
0x005a4129:	je 0x005a4131
0x005a4131:	movb (%edi), %al
0x005a4133:	incl %edi
0x005a4134:	movl %ebx, $0x2<UINT32>
0x005a4139:	jmp 0x005a40d6
0x005a413b:	movl %eax, $0x1<UINT32>
0x005a4140:	addb %dl, %dl
0x005a4142:	jne 0x005a4149
0x005a4149:	adcl %eax, %eax
0x005a414b:	addb %dl, %dl
0x005a414d:	jne 0x005a4154
0x005a4154:	jb 0x005a4140
0x005a4156:	subl %eax, %ebx
0x005a4158:	movl %ebx, $0x1<UINT32>
0x005a415d:	jne 0x005a4187
0x005a4187:	decl %eax
0x005a4188:	shll %eax, $0x8<UINT8>
0x005a418b:	movb %al, (%esi)
0x005a418d:	incl %esi
0x005a418e:	movl %ebp, %eax
0x005a4190:	movl %ecx, $0x1<UINT32>
0x005a4195:	addb %dl, %dl
0x005a4197:	jne 0x005a419e
0x005a419e:	adcl %ecx, %ecx
0x005a41a0:	addb %dl, %dl
0x005a41a2:	jne 0x005a41a9
0x005a41a9:	jb 0x005a4195
0x005a41a4:	movb %dl, (%esi)
0x005a41a6:	incl %esi
0x005a41a7:	adcb %dl, %dl
0x005a41ab:	cmpl %eax, $0x7d00<UINT32>
0x005a41b0:	jae 26
0x005a41b2:	cmpl %eax, $0x500<UINT32>
0x005a41b7:	jb 0x005a41c7
0x005a41c7:	cmpl %eax, $0x7f<UINT8>
0x005a41ca:	ja 0x005a41cf
0x005a41cc:	addl %ecx, $0x2<UINT8>
0x005a41cf:	pushl %esi
0x005a41d0:	movl %esi, %edi
0x005a41d2:	subl %esi, %eax
0x005a41d4:	rep movsb %es:(%edi), %ds:(%esi)
0x005a41d6:	popl %esi
0x005a41d7:	jmp 0x005a40d6
0x005a40f2:	movb %dl, (%esi)
0x005a40f4:	incl %esi
0x005a40f5:	adcb %dl, %dl
0x005a414f:	movb %dl, (%esi)
0x005a4151:	incl %esi
0x005a4152:	adcb %dl, %dl
0x005a415f:	movl %ecx, $0x1<UINT32>
0x005a4164:	addb %dl, %dl
0x005a4166:	jne 0x005a416d
0x005a416d:	adcl %ecx, %ecx
0x005a416f:	addb %dl, %dl
0x005a4171:	jne 0x005a4178
0x005a4178:	jb 0x005a4164
0x005a4173:	movb %dl, (%esi)
0x005a4175:	incl %esi
0x005a4176:	adcb %dl, %dl
0x005a417a:	pushl %esi
0x005a417b:	movl %esi, %edi
0x005a417d:	subl %esi, %ebp
0x005a417f:	rep movsb %es:(%edi), %ds:(%esi)
0x005a4181:	popl %esi
0x005a4182:	jmp 0x005a40d6
0x005a4144:	movb %dl, (%esi)
0x005a4146:	incl %esi
0x005a4147:	adcb %dl, %dl
0x005a4199:	movb %dl, (%esi)
0x005a419b:	incl %esi
0x005a419c:	adcb %dl, %dl
0x005a40e5:	movb %dl, (%esi)
0x005a40e7:	incl %esi
0x005a40e8:	adcb %dl, %dl
0x005a410c:	movb %dl, (%esi)
0x005a410e:	incl %esi
0x005a410f:	adcb %dl, %dl
0x005a412b:	pushl %edi
0x005a412c:	subl %edi, %eax
0x005a412e:	movb %al, (%edi)
0x005a4130:	popl %edi
0x005a4117:	movb %dl, (%esi)
0x005a4119:	incl %esi
0x005a411a:	adcb %dl, %dl
0x005a4168:	movb %dl, (%esi)
0x005a416a:	incl %esi
0x005a416b:	adcb %dl, %dl
0x005a41b9:	incl %ecx
0x005a41ba:	pushl %esi
0x005a41bb:	movl %esi, %edi
0x005a41bd:	subl %esi, %eax
0x005a41bf:	rep movsb %es:(%edi), %ds:(%esi)
0x005a41c1:	popl %esi
0x005a41c2:	jmp 0x005a40d6
0x005a4101:	movb %dl, (%esi)
0x005a4103:	incl %esi
0x005a4104:	adcb %dl, %dl
0x005a41dc:	movb %al, (%esi)
0x005a41de:	incl %esi
0x005a41df:	xorl %ecx, %ecx
0x005a41e1:	shrb %al, $0x1<UINT8>
0x005a41e4:	je 23
0x005a41e6:	adcl %ecx, $0x2<UINT8>
0x005a41e9:	movl %ebp, %eax
0x005a41eb:	pushl %esi
0x005a41ec:	movl %esi, %edi
0x005a41ee:	subl %esi, %eax
0x005a41f0:	rep movsb %es:(%edi), %ds:(%esi)
0x005a41f2:	popl %esi
0x005a41f3:	movl %ebx, $0x1<UINT32>
0x005a41f8:	jmp 0x005a40d6
